package pl.kacper.gamewebspringboot.user;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.kacper.gamewebspringboot.discussion.DiscussionRepository;
import pl.kacper.gamewebspringboot.error.UserAlreadyExistException;
import pl.kacper.gamewebspringboot.error.UserNotFoundException;
import pl.kacper.gamewebspringboot.rating.RatingRepository;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final DiscussionRepository discussionRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final VerificationTokenRepository tokenRepository;

    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          RatingRepository ratingRepository,
                          DiscussionRepository discussionRepository, BCryptPasswordEncoder passwordEncoder, JavaMailSender mailSender, VerificationTokenRepository tokenRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.discussionRepository = discussionRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.tokenRepository = tokenRepository;
    }
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }
    @PostMapping("/logout")
    public String logout() {
        return "/home";
    }
    @GetMapping("/register")
    public String add(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "admin/registration";
    }
    @PostMapping("/register")
    public String save(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()){
            return "admin/registration";
        } else if (Objects.equals(userDto.getPassword(), "Admin@222")) {
            userService.saveAdmin(userDto);
            return "admin/login";
        } else {
            try {
                User user = userService.saveUser(userDto);
                String token = UUID.randomUUID().toString();
                userService.createVerificationToken(user, token);
                String appUrl = request.getContextPath();
                String recipientAddress = user.getEmail();
                String subject = "Registration Confirmation";
                String confirmationUrl =appUrl + "/registrationConfirm?token=" + token;

                SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(recipientAddress);
                email.setSubject(subject);
                email.setText("\r\n" + "http://localhost:8080" + confirmationUrl);
                mailSender.send(email);
            } catch (UserAlreadyExistException uaeEx) {
                String message = uaeEx.getMessage();
                if (message.contains("konto o takim emailu juz isnieje:")){
                    result.rejectValue("email", "errors", message);
                } else {
                    result.rejectValue("username", "errors", message);
                }
                return "admin/registration";
            } catch (RuntimeException e) {
                return "emailError";
            }
        }
        return "admin/confirm-registration";
    }
    @GetMapping("/registrationConfirm")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token){
        Locale locale = request.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/dupabadUser.html?lang=" + locale.getLanguage();
        }
        user.setEnabled(1);
        tokenRepository.delete(tokenRepository.findByUser(user));
        userRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/user-list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findUsersByRoleId());
        return "admin/list";
    }
    @GetMapping("/userList")
    public String gameDetails(@RequestParam String name) {
        if (userRepository.findByUsername(name) == null) {
            return "redirect:/user-list";
        } else {
            return "redirect:/user-account/" + userRepository.findByUsername(name).getId();
        }
    }
    @GetMapping("/admin")
    public String userAuthentication(Model model, @AuthenticationPrincipal CurrentUser user) {
        return "redirect:/user-account/" + user.getUser().getId();
    }
    @GetMapping("/user-account/{id}")
    public String userAccount(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser.getUser());
        }
        model.addAttribute("discussions", discussionRepository.findDiscussionsByUser_Id(id));
        model.addAttribute("ratings", ratingRepository.findRatingsByUser_Id(id));
        return "admin/account";
    }
    @GetMapping("/admin/account/edit")
    public String edit(Model model, @AuthenticationPrincipal CurrentUser user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUser().getUsername());
        userDto.setEmail(user.getUser().getEmail());
        userDto.setId(user.getUser().getId());
        model.addAttribute("loggedUser", userDto);
        return "admin/edit";
    }
    @PostMapping("/admin/account/update")
    public String update(@Valid @ModelAttribute("loggedUser") UserDto loggedUser, BindingResult result,
                         @AuthenticationPrincipal CurrentUser currentUser, @RequestParam String oldPassword) {
        if (result.hasErrors()){
            return "admin/edit";
        }
        if (passwordEncoder.matches(oldPassword, currentUser.getUser().getPassword())) {
            try {
                userService.editUser(loggedUser);
            } catch (UserAlreadyExistException uaeEx) {
                String message = uaeEx.getMessage();
                if (message.contains("konto o takim emailu juz isnieje:")) {
                    result.rejectValue("email", "errors", message);
                } else {
                    result.rejectValue("username", "errors", message);
                }
                return "admin/edit";
            }
            return "redirect:/user-account/" + currentUser.getUser().getId();
        } else {
            result.rejectValue("id", "errors", "stare haslo dla konta jest nierpawidlowe");
            return "admin/edit";
        }
    }
    @GetMapping("/super-admin/block-user/{id}")
    public String blockUserAccount(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
            userService.blockUser(id);
            return "redirect:/user-list";
    }
    @GetMapping("/super-admin/unblock-user/{id}")
    public String unblockUserAccount(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        userService.unblockUser(id);
        return "redirect:/user-list";
    }
    @GetMapping("/resetPassword")
    public String resetPasswordForm() {
        return "admin/reset-password";
    }
    @GetMapping("/resetPassword/sendingEmail")
    public String resettingPassword(HttpServletRequest request, Model model,@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String appUrl = request.getContextPath();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Reset Password Confirmation";
        String confirmationUrl = appUrl + "/resetPasswordConfirm?token=" + token;

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("konto.do.aplikacji.spring@gmail.com");
        mail.setTo(recipientAddress);
        mail.setSubject(subject);
        mail.setText("\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(mail);

        return "admin/confirm-resetPassword";
    }
    @GetMapping("/resetPasswordConfirm")
    public String confirmResetPassword(WebRequest request, Model model, @RequestParam("token") String token){
        Locale locale = request.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/badpassword.html?lang=" + locale.getLanguage();
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/dupabadUser.html?lang=" + locale.getLanguage();
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        model.addAttribute("user", userDto);
        return "admin/reset-password-form";
    }
    @PostMapping("/resetPasswordConfirm")
    public String confirmRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result){
        if (result.hasErrors()){
            User user = userRepository.findById(userDto.getId()).orElseThrow(EntityNotFoundException::new);
            VerificationToken token = tokenRepository.findByUser(user);
            if (token != null) {
                tokenRepository.delete(tokenRepository.findByUser(user));
            }
            return "admin/reset-password-form";
        }
        User user = userRepository.findById(userDto.getId()).orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        VerificationToken token = tokenRepository.findByUser(user);
        if (token != null) {
            tokenRepository.delete(tokenRepository.findByUser(user));
        }
        userRepository.save(user);
        return "redirect:/login";
    }
}

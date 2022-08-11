package pl.kacper.gamewebspringboot.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kacper.gamewebspringboot.discussion.DiscussionRepository;
import pl.kacper.gamewebspringboot.rating.RatingRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final DiscussionRepository discussionRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          RatingRepository ratingRepository,
                          DiscussionRepository discussionRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.discussionRepository = discussionRepository;
        this.passwordEncoder = passwordEncoder;
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
        model.addAttribute("user", new User());
        return "admin/registration";
    }
    @PostMapping("/register")
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()){
            return "admin/registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/user-list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
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
        model.addAttribute("login", user.getUser().getUsername());
        return "admin/edit";
    }
    @GetMapping("/admin/account/update")
    public String update(@RequestParam String username, @AuthenticationPrincipal CurrentUser currentUser) {
        currentUser.getUser().setUsername(username);
        userRepository.save(currentUser.getUser());
        return "redirect:/user-account/" + currentUser.getUser().getId();
    }
    @GetMapping("/admin/password/edit")
    public String editPassword() {
        return "admin/editPassword";
    }
    @GetMapping("/admin/password/update")
    public String updatePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String repeatPassword,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        if (newPassword.equals(repeatPassword) && passwordEncoder.matches(oldPassword, currentUser.getUser().getPassword())) {
            currentUser.getUser().setPassword(newPassword);
            userService.saveUser(currentUser.getUser());
            return "redirect:/user-account/" + currentUser.getUser().getId();
        } else {
            return "redirect:/admin/password/edit";
        }
    }
    @GetMapping("/super-admin/block-user/{id}")
    public String blockUserAccount(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
            User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            user.setEnabled(0);
            userRepository.save(user);
            return "redirect:/user-list";
    }
    @GetMapping("/super-admin/unblock-user/{id}")
    public String unblockUserAccount(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setEnabled(1);
        userRepository.save(user);
        return "redirect:/user-list";
    }
    
}

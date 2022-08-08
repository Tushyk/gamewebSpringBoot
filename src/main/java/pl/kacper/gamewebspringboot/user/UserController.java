package pl.kacper.gamewebspringboot.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          RatingRepository ratingRepository,
                          DiscussionRepository discussionRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.discussionRepository = discussionRepository;
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
    @GetMapping("/admin")
    public String userAuthentication(Model model, @AuthenticationPrincipal CurrentUser user) {
        return "redirect:/user-account/" + user.getUser().getId();
    }
    @GetMapping("/user-account/{id}")
    public String userAccount(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("discussions", discussionRepository.findDiscussionsByUser_Id(id));
        model.addAttribute("ratings", ratingRepository.findRatingsByUser_Id(id));
        return "admin/account";
    }
}

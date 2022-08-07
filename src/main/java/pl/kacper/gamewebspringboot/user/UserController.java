package pl.kacper.gamewebspringboot.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserController(UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/admin/test")
    public String adminTest() {
        return "admin/welcome";
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
}

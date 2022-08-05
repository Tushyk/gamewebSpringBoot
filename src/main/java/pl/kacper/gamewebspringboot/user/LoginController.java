package pl.kacper.gamewebspringboot.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kacper.gamewebspringboot.user.Role;
import pl.kacper.gamewebspringboot.user.RoleRepository;
import pl.kacper.gamewebspringboot.user.User;
import pl.kacper.gamewebspringboot.user.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LoginController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public LoginController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
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
        return "registration";
    }
    @PostMapping("/register")
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()){
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}

package blogapp.controllers;

import blogapp.entity.User;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("title", "Главная страница");

        User user = userService.getByEmail(principal.getName());
        model.addAttribute("HelloAction", "Nice to see you, " + user.getUserName());
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница о нас");
        return "about";
    }

}
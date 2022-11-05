package blogapp.controllers;

import blogapp.entity.User;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    public MainController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница о нас");
        return "about";
    }

    @GetMapping("/sign-in-page")
    public String signIn(Model model){
        model.addAttribute("title", "Страница входа");
        return "sign-in-page";
    }

    @GetMapping("/progress")
    public String progress(Model model){
        model.addAttribute("title", "Страница прогресса");
        return "progress";
    }

    @GetMapping("/students")
    public String students(Model model){
        model.addAttribute("title", "Страница пользователей");
        model.addAttribute("users", userService.getAllUsers());
        return "students";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("title", "Страница регистрации");
        model.addAttribute("register", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegister(@ModelAttribute("register") User user){
        System.out.println(user);
        return "redirect:/";
    }
}

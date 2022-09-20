package blogapp.controllers;

import blogapp.entity.User;
import blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("about", "Страница о нас");
        return "about";
    }

    @GetMapping("/sign-in-page")
    public String signIn(Model model){
        model.addAttribute("login", "Страница входа");
        return "sign-in-page";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("register", "Регистрация на сайте");
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestParam String userName,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model){
        User user = new User(userName,email,password);
        userRepository.save(user);

        //Iterable<User> users = userRepository.findAll();
        //model.addAttribute("users", users);

        //for (User user1:users) { System.out.println(user1.toString()); }

        return "redirect:/";
    }
}

package blogapp.controllers;

import blogapp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/sign-in-page")
    public String signIn(Model model,
                         @ModelAttribute("alreadyCreated") String already,
                         @ModelAttribute("justCreated") String just,
                         @ModelAttribute("invalid") String invalid,
                         @ModelAttribute("message") String message){
        User user = new User();

        model.addAttribute("title", "Страница входа");
        model.addAttribute("doUser", user);
        return "sign-in-page";
    }
}

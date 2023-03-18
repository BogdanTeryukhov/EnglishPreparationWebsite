package blogapp.controllers;

import blogapp.entity.Role;
import blogapp.entity.User;
import blogapp.service.MailSenderService;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderService mailSender;


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("title", "Страница регистрации");
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegister(@ModelAttribute("user") User user,
                             Model model){


        if (userService.isExists(user.getEmail())){
            model.addAttribute("alreadyCreated", "You already have an account, please Sign In!");
        }else {
            user.setActive(false);
            if (Objects.equals(user.getEmail(), "andrejteryukhov5@gmail.com")){
                user.setRoles(Collections.singleton(Role.ADMIN));
            }else {
                user.setRoles(Collections.singleton(Role.USER));
            }
            user.setActivationCode(UUID.randomUUID().toString());
            model.addAttribute("justCreated", "Now you have an account! Please, Log in");

            userService.saveUser(user);

            String message = String.format(
                    "Hello, %s! \n" + "Welcome to our Website. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUserName(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(),"Activation Code", message);


        }
        return "redirect:/sign-in-page";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userService.activationUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated");
            User user = userService.findUserByActivationCode(code);
            user.setActive(true);
            user.setActivationCode(null);
            userService.saveUser(user);
        }else{
            model.addAttribute("message", "Activation code is not found");
        }

        return "redirect:/sign-in-page";
    }
}

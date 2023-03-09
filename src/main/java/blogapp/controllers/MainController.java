package blogapp.controllers;

import antlr.StringUtils;
import blogapp.entity.Role;
import blogapp.entity.User;
import blogapp.repository.UserRepository;
import blogapp.service.MailSenderService;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    private MailSenderService mailSender;

    @Autowired
    public MainController(UserService userService) {
        super();
        this.userService = userService;
    }

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

    @GetMapping("/sign-in-page")
    public String signIn(Model model,
                         @ModelAttribute("alreadyCreated") String already,
                         @ModelAttribute("justCreated") String just,
                         @ModelAttribute("invalid") String invalid,
                         @ModelAttribute("message") String message){
        User user = new User();

        model.addAttribute("title", "Страница входа");
        model.addAttribute("doUser", user);
        //System.out.println(user.getUserName());
        return "sign-in-page";
    }


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
            System.out.println(user);
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

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("title", "Страница пользователей");
        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }


    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") User user,
                             Model model){
        User existingUser = userService.getUserById(id);

        existingUser.setId(user.getId());
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        userService.updateUser(existingUser);
        return "redirect:/users";
    }
}
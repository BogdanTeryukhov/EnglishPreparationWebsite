package blogapp.controllers;

import blogapp.entity.Role;
import blogapp.entity.User;
import blogapp.repository.UserRepository;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collections;
import java.util.Objects;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("title", "Главная страница");

        User user = userService.getByEmail(principal.getName());
        model.addAttribute("HelloAction", "Nice to see you again, " + user.getUserName());
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
                         @ModelAttribute("invalid") String invalid){
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
            user.setActive(true);
            if (Objects.equals(user.getEmail(), "andrejteryukhov5@gmail.com")){
                user.setRoles(Collections.singleton(Role.ADMIN));
            }else {
                user.setRoles(Collections.singleton(Role.USER));
            }
            model.addAttribute("justCreated", "Now you have an account! Please, Log in");

            userService.saveUser(user);
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
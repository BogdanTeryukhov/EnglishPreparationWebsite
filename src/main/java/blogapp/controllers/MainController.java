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
import java.util.Collections;
import java.util.Objects;

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
        //model.addAttribute("button", needButton);
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
       // System.out.println(user);
       // model.addAttribute("name", "Hi"+ user.getUserName());
        return "sign-in-page";
    }

    //не нужно, вроде как
    @PostMapping("/sign-in-page")
    public String doSignIn(@ModelAttribute("doUser") User user,
                           Model model){
        System.out.println(user);
        for (User value : userService.getAllUsers()) {
            if (Objects.equals(value.getEmail(), user.getEmail())
                    && Objects.equals(value.getPassword(), user.getPassword())) {
                //model.addAttribute("NeedButton", false);
                return "redirect:/";
            }
        }

        model.addAttribute("invalid","Invalid Email or Password");
        return "redirect:/sign-in-page";
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

        if (user.getId()!=null){
            return "registration";
        }else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            model.addAttribute("justCreated", "Now you have an account! Please, Log in");
            userService.saveUser(user);
            return "redirect:/sign-in-page";
        }
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
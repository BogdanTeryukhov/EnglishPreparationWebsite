package blogapp.controllers;

import blogapp.entity.User;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        model.addAttribute("doUser", new User());
        return "sign-in-page";
    }
    @PostMapping("/sign-in-page")
    public String doSignIn(@ModelAttribute("doUser") User user){
        System.out.println(user);
        //List<User> userList = userService.getAllUsers();
        for (User value : userService.getAllUsers()) {
            if (Objects.equals(value.getEmail(), user.getEmail())
                    && Objects.equals(value.getPassword(), user.getPassword())) {
                //System.out.println(value.getPassword() + "---" + user.getPassword()));
                return "redirect:/";
            }
        }
        return "redirect:/registration";
    }

    @GetMapping("/users")
    public String students(Model model){
        model.addAttribute("title", "Страница пользователей");
        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("title", "Страница регистрации");
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegister(@ModelAttribute("user") User user){
        for (User value: userService.getAllUsers()) {
            if (Objects.equals(value.getUserName(), user.getUserName())
                    && Objects.equals(value.getEmail(), user.getEmail())
                    && Objects.equals(value.getPassword(), user.getPassword())){
                return "redirect:/sign-in-page";
            }
        }

        userService.saveUser(user);
        return "redirect:/";
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
        return "redirect:/students";
    }
}

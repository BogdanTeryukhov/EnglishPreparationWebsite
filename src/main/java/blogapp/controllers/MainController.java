package blogapp.controllers;

import blogapp.entity.User;
import blogapp.repository.UserRepository;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Objects;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
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
        model.addAttribute("title", "Страница входа");
        model.addAttribute("doUser", new User());
        return "sign-in-page";
    }

    @PostMapping("/sign-in-page")
    public String doSignIn(@ModelAttribute("doUser") User user,
                           Model model){

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

    @GetMapping("/users")
    public String users(Model model){
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
    @Transactional
    public String doRegister(@ModelAttribute("user") User user,
                             Model model){
        for (User value: userService.getAllUsers()) {
            if (Objects.equals(value.getUserName(), user.getUserName())
                    && Objects.equals(value.getEmail(), user.getEmail())
                    && Objects.equals(value.getPassword(), user.getPassword())){
                //model.addAttribute("needButton", false);
                model.addAttribute("alreadyCreated", "You already have an account! Please, Log in");
                return "redirect:/sign-in-page";
            }
        }

        model.addAttribute("justCreated", "Now you have an account! Please, Log in");
        //model.addAttribute("needButton", false)

        userService.saveUser(user);
        return "redirect:/sign-in-page";
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
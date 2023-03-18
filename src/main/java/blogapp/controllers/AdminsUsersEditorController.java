package blogapp.controllers;

import blogapp.entity.User;
import blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminsUsersEditorController {

    @Autowired
    private UserService userService;

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

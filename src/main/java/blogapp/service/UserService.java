package blogapp.service;

import blogapp.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User getUserById(Long id);
    User updateUser(User user);
    void deleteUserById(Long Id);
}

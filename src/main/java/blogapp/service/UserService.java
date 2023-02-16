package blogapp.service;

import blogapp.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void updateUser(User user);
    void deleteUserById(Long Id);
    User getByEmail(String email);
    boolean isExists(String email);
}

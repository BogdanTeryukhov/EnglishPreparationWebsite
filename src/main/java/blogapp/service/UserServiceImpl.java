package blogapp.service;

import blogapp.entity.User;
import blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.get();
    }

    @Override
    public boolean isExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean activationUser(String code) {
        User user = userRepository.findByActivationCode(code);
        System.out.println(user+"\n");
        if (user == null){
            return false;
        }

        userRepository.save(user);

        return true;
    }

    @Override
    public User findUserByActivationCode(String code) {
        return userRepository.findByActivationCode(code);
    }
}

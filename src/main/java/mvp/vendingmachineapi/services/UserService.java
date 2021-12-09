package mvp.vendingmachineapi.services;

import mvp.vendingmachineapi.dto.DeleteMessageResponse;
import mvp.vendingmachineapi.dto.NewUser;
import mvp.vendingmachineapi.exceptions.UserNotFoundException;
import mvp.vendingmachineapi.models.User;
import mvp.vendingmachineapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(NewUser newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        user.setRoles(new HashSet<>());

        newUser.getRoles().forEach(role -> user.getRoles().add(new SimpleGrantedAuthority(role)));

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public DeleteMessageResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        return new DeleteMessageResponse();
    }

}

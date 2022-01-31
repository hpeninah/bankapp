package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.data.models.User;
import springboot.api.bankapp.data.repository.CustomerRepository;
import springboot.api.bankapp.data.repository.RoleRepository;
import springboot.api.bankapp.data.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    //Return all users
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //Return a user by id
    public User getUser(Long userId) {
        return userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new IllegalStateException("User with ID: " + userId + " does not exist."));
    }

    //Create a new user
    public User createUser(User user){
        customerRepository.save(user.getCustomer());
        Role role = roleRepository.findById(user.getRole().getRoleId()).orElseThrow(() -> new IllegalStateException("Role ID: " + user.getRole().getRoleId() + " does not exist."));
        user.setRole(role);
        return userRepository.save(user);
    }

    //Login a user
    public User loginUser(User user) {
        return userRepository.loginUser(user.getUsername(), user.getPassword());
    }

    //Update a user
    @Transactional
    public User updateUser(Long userId, String password) {
        User updateUser = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new IllegalStateException("User ID: " + userId + " does not exist."));

        if(password != null) {
            updateUser.setPassword(password);
            return userRepository.save(updateUser);
        } else {
            throw new IllegalStateException("Password is required.");
        }
    }
    //Delete a user
    public boolean deleteUser(Long userId) {
        userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new IllegalStateException("User ID: " + userId + " does not exist."));

        try{
            userRepository.deleteById((Math.toIntExact(userId)));
        } catch (Exception exception) {
            System.out.println("Error: " + exception );
            return false;
        }

        return true;
    }
}

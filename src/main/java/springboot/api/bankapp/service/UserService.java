package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.data.models.User;
import springboot.api.bankapp.data.repository.CustomerRepository;
import springboot.api.bankapp.data.repository.RoleRepository;
import springboot.api.bankapp.data.repository.UserRepository;
import springboot.api.bankapp.exceptions.InvalidInputException;
import springboot.api.bankapp.exceptions.InvalidLoginException;
import springboot.api.bankapp.exceptions.MissingFieldException;
import springboot.api.bankapp.exceptions.UserNotFoundException;

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
    public User getUser(Long userId) throws UserNotFoundException {
        return userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " does not exist."));
    }

    //Create a new user
    public User createUser(User user) throws UserNotFoundException{
        customerRepository.save(user.getCustomer());
        Role role = roleRepository.findById(user.getRole().getRoleId()).orElseThrow(() -> new UserNotFoundException("Role ID: " + user.getRole().getRoleId() + " does not exist."));
        user.setRole(role);
        return userRepository.save(user);
    }

    //Login a user
    public User loginUser(User user) throws InvalidLoginException {
        User verifyUser = userRepository.loginUser(user.getUsername(), user.getPassword());

        if(verifyUser == null) {
            throw new InvalidLoginException("Invalid username and password");
        }
        return verifyUser;
    }

    //Update a user
    @Transactional
    public User updateUser(Long userId, String password) throws UserNotFoundException, MissingFieldException, InvalidInputException {
        User updateUser = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new UserNotFoundException("User ID: " + userId + " does not exist."));

        if (password == null) {
            throw new MissingFieldException("Password is required.");
        } else if (password.length() < 8) {
            throw new InvalidInputException("Password must be greater than 8 characters.");
        }

        updateUser.setPassword(password);
        return userRepository.save(updateUser);
    }

    //Delete a user
    public boolean deleteUser(Long userId) throws UserNotFoundException {
        userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new UserNotFoundException("User ID: " + userId + " does not exist."));
        userRepository.deleteById((Math.toIntExact(userId)));
        return true;
    }
}

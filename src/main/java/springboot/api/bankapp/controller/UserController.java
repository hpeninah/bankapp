package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.User;
import springboot.api.bankapp.exceptions.InvalidInputException;
import springboot.api.bankapp.exceptions.InvalidLoginException;
import springboot.api.bankapp.exceptions.MissingFieldException;
import springboot.api.bankapp.exceptions.UserNotFoundException;
import springboot.api.bankapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getUsers () {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) throws UserNotFoundException{
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws InvalidLoginException{
        return userService.loginUser(user);
    }

    @PutMapping("/{userId}/{newPs}")
    public User updateUser(@PathVariable("userId") Long userId, @PathVariable("newPs") String newPs) throws UserNotFoundException, InvalidInputException, MissingFieldException {
        return userService.updateUser(userId, newPs);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException{
        return userService.deleteUser(userId);
    }
}

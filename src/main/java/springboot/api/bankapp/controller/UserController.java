package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.User;
import springboot.api.bankapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers () {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @PutMapping("/{userId}/{newPs}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @PathVariable("newPs") String newPs) {
        try {
            User updateUser = userService.updateUser(userId, newPs);
            return new ResponseEntity<User>(updateUser, HttpStatus.OK);
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}

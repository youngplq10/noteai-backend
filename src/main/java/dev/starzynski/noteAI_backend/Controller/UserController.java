package dev.starzynski.noteAI_backend.Controller;

import dev.starzynski.noteAI_backend.Model.User;
import dev.starzynski.noteAI_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return new ResponseEntity<String> (userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/public/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        return new ResponseEntity<String> (userService.loginUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/auth/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<User> (userService.getUserByUsername(username), HttpStatus.OK);
    }
}

package com.image.photos.controller;

import com.image.photos.controller.api.User;
import com.image.photos.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        log.info("Registered user successfully: {}", user.getUsername());
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Endpoint to get user details
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        log.info("User details Retrieved successfully for user: {}", username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

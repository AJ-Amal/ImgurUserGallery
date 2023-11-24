package com.image.photos.service;

import com.image.photos.controller.api.User;
import com.image.photos.exception.UserNotFoundException;
import com.image.photos.exception.UsernameException;
import com.image.photos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameException("Username already exists");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(hashedPassword);

        //userRepository.save(newUser);
        return userRepository.save(newUser);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public boolean authenticateUser(String username, String password) {
        // Retrieve user by username from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the provided password matches the stored hashed password
        return passwordEncoder.matches(password, user.getPassword());
    }

}

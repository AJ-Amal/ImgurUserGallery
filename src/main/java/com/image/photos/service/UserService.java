package com.image.photos.service;

import com.image.photos.controller.api.User;

public interface UserService {

    User registerUser(User user);

    User getUserByUsername(String username);
}

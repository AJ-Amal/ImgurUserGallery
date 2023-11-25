package com.image.photos.service;

import com.image.photos.ImgurApiService.ImgurApiService;
import com.image.photos.controller.api.Image;
import com.image.photos.controller.api.User;
import com.image.photos.exception.ImageNotFoundException;
import com.image.photos.repository.ImageRepository;
import com.image.photos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImgurApiService imgurApiService;

    @Override
    public void saveImageForUser(String username, String imgUrl) {
        // Retrieve the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Image image = new Image();
        image.setImageUrl(imgUrl);
        image.setUser(user);
        imageRepository.save(image);
    }

    @Override
    public List<Image> getImagesByUsername(String username) {
        // Retrieve the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return imageRepository.findByUserId(user.getId());
    }

    @Override
    public void deleteImage(Long imageId, String token) {
        // Retrieve the image by ID
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Image not found"));
        imgurApiService.deleteImage(image.getImageUrl(),token);

        // Delete image from the database
        imageRepository.delete(image);
    }

}

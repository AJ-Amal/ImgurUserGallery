package com.image.photos.controller;


import com.image.photos.ImgurApiService.ImgurApiService;
import com.image.photos.controller.api.Image;
import com.image.photos.service.ImageServiceImpl;
import com.image.photos.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private ImgurApiService imgurApiService;

    @Autowired
    private UserServiceImpl userService;


    // Endpoint to get upload user image
    @PostMapping("/upload/{username}")
    public ResponseEntity<String> uploadImage(@PathVariable String username, @RequestParam("file") MultipartFile file) {

        /*if (!authenticateUser(username, authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid credentials or user not found");
        }*/
        try {
            // Upload image to Imgur and get the Imgur URL
            String imgurUrl = imgurApiService.uploadImage(file);
            // Save the Imgur URL associated with the user
            imageService.saveImageForUser(username, imgurUrl);
            log.info("Image uploaded successfully for user: {}", username);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            // Log the exception for debugging
            log.error("Error uploading image for user: {}", username, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }


    // Endpoint to view updated list of images with the user profile
    @GetMapping("/view/{username}")
    public ResponseEntity<List<Image>> viewImages(@PathVariable String username) {

        // Retrieve images for the authenticated user
        List<Image> userImages = imageService.getImagesByUsername(username);
        log.info("Image Retrieved successfully for user: {}", username);
        return ResponseEntity.ok(userImages);
    }


    // Endpoint to delete user image
    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<String> deleteImageForAuthenticatedUser(@PathVariable Long imageId) {

        // Delete the specified image for the authenticated user
        imageService.deleteImage(imageId);
        log.info("Image deleted successfully for user: {}", imageId);
        return ResponseEntity.ok("Image deleted successfully");
    }


    /*private boolean authenticateUser(String username, String authorizationHeader) {
        // Extract username and password from the authorization header (you may use Spring Security for this)
        String[] credentials = extractCredentials(authorizationHeader);

        // Authenticate the user
        return userService.authenticateUser(credentials[0], credentials[1]);
    }

    private String[] extractCredentials(String authorizationHeader) {
        // In a real application, you might use a more sophisticated method to extract credentials
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        return credentials.split(":", 2);
    }*/
}

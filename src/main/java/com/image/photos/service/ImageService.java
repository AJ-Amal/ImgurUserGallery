package com.image.photos.service;

import com.image.photos.controller.api.Image;

import java.util.List;

public interface ImageService {

    void saveImageForUser(String username, String imgUrl);

    List<Image> getImagesByUsername(String username);

    void deleteImage(Long imageId, String token);
}

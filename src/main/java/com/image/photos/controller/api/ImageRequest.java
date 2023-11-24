package com.image.photos.controller.api;

public class ImageRequest {

    private String imageUrl;

    // Constructors, getters, and setters

    public ImageRequest() {
        // Default constructor required for JSON deserialization
    }

    public ImageRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

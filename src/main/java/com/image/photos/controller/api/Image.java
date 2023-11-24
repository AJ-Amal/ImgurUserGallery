package com.image.photos.controller.api;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Image() {
        // Default constructor
    }

    public Image(String imageUrl, User user) {
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
            return imageUrl;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


package com.image.photos.repository;

import com.image.photos.controller.api.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByUserId(Long userId);
}

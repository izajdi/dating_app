package com.example.sp.image.controller;

import com.example.sp.Photo.model.Photo;
import com.example.sp.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("addImage")
    public ResponseEntity addPhoto(@RequestParam("file") MultipartFile file, @RequestParam("user_id") Long userId) {
        imageRepository.saveImageFile(userId, file);
        return ResponseEntity.ok().build();
    }
}

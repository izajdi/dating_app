package com.example.sp.Photo.controller;

import com.example.sp.Photo.repository.PhotoRepository;
import com.example.sp.Photo.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "addPhoto")
    public ResponseEntity<Long> addPhoto(@RequestParam("file") MultipartFile file, @RequestParam("user_id") Long userId) {
        try {
            byte[] photoInBytes = file.getBytes();
            Photo photo = Photo.builder()
                    .userId(userId)
                    .photo(photoInBytes)
                    .build();
            photoRepository.save(photo);
        } catch (IOException e) {
            throw new IllegalStateException("Error occured during saving photo");
        }
        return ResponseEntity
                .ok()
                .build();
    }
}

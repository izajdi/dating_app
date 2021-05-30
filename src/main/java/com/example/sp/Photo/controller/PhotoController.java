package com.example.sp.Photo.controller;

import com.example.sp.Photo.repository.PhotoRepository;
import com.example.sp.Photo.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("addPhoto")
    public ResponseEntity<Long> addPhoto(@RequestBody Photo photo) {
        photoRepository.save(photo);
        return ResponseEntity
                .ok()
                .build();
    }
}

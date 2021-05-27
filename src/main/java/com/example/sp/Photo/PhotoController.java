package com.example.sp.Photo;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @PostMapping("addPhoto")
    public ResponseEntity<Long> addPhoto(@RequestBody Photo photo) {
        photoRepository.save(photo);
        return ResponseEntity
                .ok(photo.getId());
    }
}

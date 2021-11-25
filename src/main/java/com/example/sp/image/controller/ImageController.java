package com.example.sp.image.controller;

import com.example.sp.image.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("addImage/{id}")
    public ResponseEntity addPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long userId) {
        imageRepository.saveImageFile(userId, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "image/{id}")
    public void renderImage(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
        byte[] userImage = imageRepository.getImage(id);
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(userImage);
        IOUtils.copy(is, response.getOutputStream());
    }
}

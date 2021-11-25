package com.example.sp.image.controller;

import com.example.sp.image.control.ImageService;
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
    ImageService imageService;

    @PostMapping("addImage/{user_id}")
    public ResponseEntity addPhoto(@RequestParam("file") MultipartFile file, @PathVariable("user_id") Long userId) {
        imageService.save(userId, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "image/{user_id}")
    public void renderImage(@PathVariable("user_id") Long userId, HttpServletResponse response) throws Exception {
        byte[] userImage = imageService.get(userId);
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(userImage);
        IOUtils.copy(is, response.getOutputStream());
    }
}

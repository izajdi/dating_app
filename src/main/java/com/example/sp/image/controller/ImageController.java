package com.example.sp.image.controller;

import com.example.sp.image.control.ImageProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageProxy proxy;

    @PostMapping("add/{user_id}")
    public ResponseEntity addImage(@RequestParam("file") MultipartFile file, @PathVariable("user_id") Long userId) {
        return proxy.saveImage(userId, file);
    }

    @GetMapping(value = "get/{user_id}")
    public void renderImage(@PathVariable("user_id") Long userId, HttpServletResponse response) throws Exception {
        proxy.renderImage(userId, response);
    }

    @PutMapping(value = "upload/{user_id}")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("user_id") Long userId) {
        return proxy.uploadImage(userId, file);
    }
}

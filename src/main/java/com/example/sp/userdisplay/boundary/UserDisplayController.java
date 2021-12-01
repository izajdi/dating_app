package com.example.sp.userdisplay.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/display")
public class UserDisplayController {

    @Autowired
    UserDisplayProxy proxy;

    @GetMapping("get/{user_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity get(@PathVariable("user_id") Long userId) {
        return proxy.get(userId);
    }
}

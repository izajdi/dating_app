package com.example.sp.userpreferences.boundary;

import com.example.sp.userpreferences.entity.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferences")
public class UserPreferencesController {

    @Autowired
    UserPreferencesProxy proxy;

    @PutMapping("update")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity update(@RequestBody UserPreferences userPreferences) {
        return proxy.update(userPreferences);
    }

    @GetMapping("get/{user_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity get(@PathVariable("user_id") Long userId) {
        return proxy.get(userId);
    }
}

package com.example.sp.userpreferences.controller;

import com.example.sp.userpreferences.control.UserPreferencesProxy;
import com.example.sp.userpreferences.model.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferences")
public class UserPreferencesController {

    @Autowired
    UserPreferencesProxy proxy;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody UserPreferences userPreferences) {
        return proxy.save(userPreferences);
    }

    @GetMapping("get/{user_id}")
    public ResponseEntity get(@PathVariable("user_id") Long userId) {
        return proxy.get(userId);
    }

    @GetMapping("getPossibleMatch/{user_id}")
    public ResponseEntity getProposedUsers(@PathVariable("user_id") Long userId) {
        return proxy.getUsersToMatch(userId);
    }
}

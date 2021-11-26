package com.example.sp.user.controller;


import com.example.sp.user.model.User;
import com.example.sp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sp.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        }
        
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> currentUserDate = userRepository.findById(id);
        return currentUserDate
                .map(value -> new ResponseEntity<>(userRepository.save(userService.getUserEntityToUpdate(value, user)),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(user, HttpStatus.BAD_REQUEST));
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        Optional<User> userFromDateBase = userRepository.findByEmail(user.getEmail());
        return userFromDateBase
                .filter(userFromDb -> userService.arePasswordsEquals(userFromDb.getPassword(), user.getPassword()))
                .map(userFromDb -> new ResponseEntity<>(userFromDb, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/addLikedUser")
    public ResponseEntity<User> addLikedUser(@RequestBody User user, @RequestParam("id") Long id) {
        userService.addLikedUserId(user, id);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



}

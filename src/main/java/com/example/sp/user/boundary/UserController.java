package com.example.sp.user.boundary;


import com.example.sp.user.control.UserRepository;
import com.example.sp.user.control.UserService;
import com.example.sp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserProxy proxy;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("users")
    public ResponseEntity getUsers() {
        return proxy.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("user/add")
    public ResponseEntity addUser(@RequestBody User user) {
        return proxy.addUser(user);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("user/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return proxy.updateUser(id, user);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        return proxy.login(user);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/updateLikedUser/{id}")
    public ResponseEntity updateLikedUser(@PathVariable Long id, @RequestParam("liked_user_id") Long likedUserId) {
        return proxy.updateLikedUser(id, likedUserId);
    }
}

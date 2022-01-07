package com.example.sp.user.boundary;

import com.example.sp.common.error.control.ErrorMapper;
import com.example.sp.common.error.entity.Error;
import com.example.sp.common.validators.user.UserEmailExistenceValidator;
import com.example.sp.common.validators.user.UserExistenceValidator;
import com.example.sp.user.control.UserRepository;
import com.example.sp.user.control.UserService;
import com.example.sp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProxy {

    @Autowired
    UserRepository repository;
    @Autowired
    UserService service;
    @Autowired
    UserExistenceValidator userExistenceValidator;
    @Autowired
    UserEmailExistenceValidator userEmailExistenceValidator;
    @Autowired
    ErrorMapper errorMapper;

    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity addUser(User user) {
        Optional<Error> emailExistenceError = userEmailExistenceValidator.validate(user.getEmail());
        if (emailExistenceError.isPresent()) {
            return errorMapper.mapToResponseEntity(emailExistenceError.get());
        }
        return ResponseEntity.ok(repository.save(user));
    }

    public ResponseEntity updateUser(Long id, User user) {
        Optional<User> dbUser = repository.findById(id);
        Optional<Error> userExistenceError = userExistenceValidator.validate(dbUser, id);
        if (userExistenceError.isPresent()) {
            return errorMapper.mapToResponseEntity(userExistenceError.get());
        }
        return ResponseEntity.ok(repository.save(service.getUserEntityToUpdate(dbUser.get(), user)));
    }

    public ResponseEntity login(User user) {
        Optional<User> userFromDateBase = repository.findByEmail(user.getEmail());
        return userFromDateBase
                .filter(userFromDb -> service.arePasswordsEquals(userFromDb.getPassword(), user.getPassword()))
                .map(userFromDb -> new ResponseEntity<>(userFromDb, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    public ResponseEntity updateLikedUser(Long userId, Long likedUserId) {
        Optional<User> user = repository.findById(userId);
        Optional<Error> userExistenceError = userExistenceValidator.validate(user, userId);
        if (userExistenceError.isPresent()) {
            return errorMapper.mapToResponseEntity(userExistenceError.get());
        }
        user.get().setLikedUserId(String.valueOf(likedUserId));
        repository.save(user.get());
        return ResponseEntity.ok().build();
    }
}

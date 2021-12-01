package com.example.sp.userdisplay.control;

import com.example.sp.user.repository.UserRepository;
import com.example.sp.userpreferences.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDisplayValidator {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPreferencesRepository userPreferencesRepository;

    public Optional<Error> validate(Long userId) {
        if (!userRepository.existsById(userId)) {

        }
    }
}

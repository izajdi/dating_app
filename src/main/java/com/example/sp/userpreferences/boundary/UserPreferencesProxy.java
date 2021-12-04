package com.example.sp.userpreferences.boundary;

import com.example.sp.common.error.control.ErrorMapper;
import com.example.sp.common.error.model.Error;
import com.example.sp.common.validators.userpreferences.UserPreferencesExistenceValidator;
import com.example.sp.userpreferences.entity.UserPreferences;
import com.example.sp.userpreferences.control.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPreferencesProxy {

    @Autowired
    UserPreferencesRepository repository;
    @Autowired
    UserPreferencesExistenceValidator userPreferencesExistenceValidator;
    @Autowired
    ErrorMapper errorMapper;

    public ResponseEntity update(UserPreferences userPreferences) {
        repository.save(userPreferences);
        return ResponseEntity
                .ok(String.format("UserPrefernces was saved for user with id: %d", userPreferences.getUserId()));
    }

    public ResponseEntity get(Long userId) {
        Optional<UserPreferences> userPreferences = repository.findById(userId);
        Optional<Error> error = userPreferencesExistenceValidator.validate(userPreferences, userId);
        if (error.isPresent()) {
            return errorMapper.mapToResponseEntity(error.get());
        }
        return ResponseEntity
                .ok(userPreferences.get());
    }
}

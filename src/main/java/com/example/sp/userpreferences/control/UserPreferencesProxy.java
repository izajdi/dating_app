package com.example.sp.userpreferences.control;

import com.example.sp.common.error.control.ErrorMapper;
import com.example.sp.common.error.model.Error;
import com.example.sp.user.model.User;
import com.example.sp.userpreferences.model.UserPreferences;
import com.example.sp.userpreferences.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserPreferencesProxy {

    @Autowired
    UserPreferencesRepository repository;
    @Autowired
    UserPreferencesService service;
    @Autowired
    ErrorMapper errorMapper;

    public ResponseEntity update(UserPreferences userPreferences) {
        repository.save(userPreferences);
        return ResponseEntity
                .ok(String.format("UserPrefernces was saved for user with id: %d", userPreferences.getUserId()));
    }

    public ResponseEntity get(Long userId) {
        Optional<Error> error = service.validate(userId);
        if (error.isPresent()) {
            return errorMapper.mapToResponseEntity(error.get());
        }
        Optional<UserPreferences> userPreferences = repository.findById(userId);
        return ResponseEntity
                .ok(userPreferences.get());
    }

    public ResponseEntity getUsersToMatch(Long userId) {
        List<User> proposedUsersInProperOrder = service.getProposedUsersInProperOrder(userId);
        return ResponseEntity.ok(proposedUsersInProperOrder);
    }

}

package com.example.sp.common.validators.userpreferences;

import com.example.sp.common.error.model.Error;
import com.example.sp.common.error.model.ErrorCode;
import com.example.sp.userpreferences.entity.UserPreferences;
import com.example.sp.userpreferences.control.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPreferencesExistenceValidator {

    @Autowired
    UserPreferencesRepository userPreferencesRepository;

    public Optional<Error> validate(Long userId) {
        if (!userPreferencesRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("Cannot find UserPreferences for user with id: %d", userId),
                    ErrorCode.NO_USER_PREFERENCES_FOR_GIVEN_USER_ERROR));
        }
        return Optional.empty();
    }

    public Optional<Error> validate(Optional<UserPreferences> userPreferences, Long userId) {
        if (userPreferences.isEmpty()) {
            return Optional.of(new Error(String.format("Cannot find UserPreferences for user with id: %d", userId),
                    ErrorCode.NO_USER_PREFERENCES_FOR_GIVEN_USER_ERROR));
        }
        return Optional.empty();
    }
}

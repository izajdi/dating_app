package com.example.sp.userpreferences.control;

import com.example.sp.error.model.Error;
import com.example.sp.error.model.ErrorCode;
import com.example.sp.user.model.User;
import com.example.sp.user.repository.UserRepository;
import com.example.sp.userpreferences.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferencesService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPreferencesRepository userPreferencesRepository;

    public List<User> getProposedUsersInProperOrder(Long userId) {
        userPreferencesRepository.findById(userId)
    }

    public Optional<Error> validateForQuery(Long userId) {
        if (!userPreferencesRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("Cannot find UserPreferences for user with id: %d", userId),
                    ErrorCode.NO_USER_PREFERENCES_FOR_GIVEN_USER_ERROR));
        }
        return Optional.empty();
    }
}

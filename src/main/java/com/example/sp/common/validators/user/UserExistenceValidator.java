package com.example.sp.common.validators.user;

import com.example.sp.common.error.entity.Error;
import com.example.sp.common.error.entity.ErrorCode;
import com.example.sp.user.entity.User;
import com.example.sp.user.control.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserExistenceValidator {

    @Autowired
    UserRepository userRepository;

    public Optional<Error> validate(Optional<User> user, Long userId) {
        if (user.isEmpty()) {
            return Optional.of(new Error(String.format("User with given id: %d does not exist in db", userId),
                    ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR));
        }
        return Optional.empty();
    }

    public Optional<Error> validate(Long userId) {
        if (!userRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("User with given id: %d does not exist in db", userId),
                    ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR));
        }
        return Optional.empty();
    }

}

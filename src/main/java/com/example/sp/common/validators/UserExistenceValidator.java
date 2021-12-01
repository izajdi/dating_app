package com.example.sp.common.validators;

import com.example.sp.common.error.model.Error;
import com.example.sp.common.error.model.ErrorCode;
import com.example.sp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserExistenceValidator {

    @Autowired
    UserRepository userRepository;

    public Optional<Error> validate(Long userId) {
        if (!userRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("User with given id: %d does not exist in db, so uplouding image is impossible", userId),
                    ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR));
        }
        return Optional.empty();
    }

}

package com.example.sp.common.validators.user;

import com.example.sp.common.error.entity.Error;
import com.example.sp.common.error.entity.ErrorCode;
import com.example.sp.user.control.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEmailExistenceValidator {

    @Autowired
    UserRepository repository;

    public Optional<Error> validate(String userEmail) {
        if (repository.existsUserByEmail(userEmail)) {
            return Optional.of(new Error(String.format("User with email : %s exist in db", userEmail),
                    ErrorCode.USER_EMAIL_BUSY_ERROR));
        }
        return Optional.empty();
    }
}

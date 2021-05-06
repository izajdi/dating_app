package com.example.sp.service;

import com.example.sp.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserEntityToUpdate(User currentUser, User userToUpdate) {
        return User.UserBuilder.builder()
                .id(currentUser.getId())
                .name(getCurrentName(currentUser, userToUpdate))
                .dateOfBirthday(getCurrentDateOfBirthday(currentUser, userToUpdate))
                .email(getCurrentEmail(currentUser, userToUpdate))
                .country(getCurrentCountry(currentUser, userToUpdate))
                .city(getCurrentCity(currentUser, userToUpdate))
                .description(getCurrentDescription(currentUser, userToUpdate))
                .password(getCurrentPassword(currentUser, userToUpdate))
                .build();
    }

    private String getCurrentName(User currentUser, User userToUpdate) {
        if(userToUpdate.getName().isEmpty()) {
            return currentUser.getName();
        }
        return userToUpdate.getName();
    }

    private String getCurrentDateOfBirthday(User currentUser, User userToUpdate) {
        if(userToUpdate.getDateOfBirthday().isEmpty()) {
            return currentUser.getDateOfBirthday();
        }
        return userToUpdate.getDateOfBirthday();
    }

    private String getCurrentEmail(User currentUser, User userToUpdate) {
        if(userToUpdate.getEmail().isEmpty()) {
            return currentUser.getEmail();
        }
        return userToUpdate.getEmail();
    }

    private String getCurrentCountry(User currentUser, User userToUpdate) {
        if(userToUpdate.getCountry().isEmpty()) {
            return currentUser.getCountry();
        }
        return userToUpdate.getCountry();
    }

    private String getCurrentCity(User currentUser, User userToUpdate) {
        if(userToUpdate.getCity().isEmpty()) {
            return currentUser.getCity();
        }
        return userToUpdate.getCity();
    }

    private String getCurrentDescription(User currentUser, User userToUpdate) {
        if(userToUpdate.getDescription().isEmpty()) {
            return currentUser.getDescription();
        }
        return userToUpdate.getDescription();
    }

    private String getCurrentPassword(User currentUser, User userToUpdate) {
        if(userToUpdate.getPassword().isEmpty()) {
            return currentUser.getPassword();
        }
        return userToUpdate.getPassword();
    }
}

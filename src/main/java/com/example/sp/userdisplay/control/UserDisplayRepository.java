package com.example.sp.userdisplay.control;

import com.example.sp.user.repository.UserRepository;
import com.example.sp.userdisplay.entity.UserDisplay;
import com.example.sp.userpreferences.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDisplayRepository {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPreferencesRepository userPreferencesRepository;

    public List<UserDisplay> get(Long userId) {

    }
}

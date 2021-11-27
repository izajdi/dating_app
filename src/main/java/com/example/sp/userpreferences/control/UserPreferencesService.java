package com.example.sp.userpreferences.control;

import com.example.sp.error.model.Error;
import com.example.sp.error.model.ErrorCode;
import com.example.sp.user.model.User;
import com.example.sp.user.repository.UserRepository;
import com.example.sp.userpreferences.model.UserPreferences;
import com.example.sp.userpreferences.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserPreferencesService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPreferencesRepository userPreferencesRepository;

    public List<User> getProposedUsersInProperOrder(Long userId) {
        UserPreferences userPreferences = userPreferencesRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("UserPreferences should be present on this stage"));
        return userRepository.findAllByGender(userPreferences.getGender());
        //// do zrobienia cała logika


    }

    public Optional<Error> validate(Long userId) {
        if (!userPreferencesRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("Cannot find UserPreferences for user with id: %d", userId),
                    ErrorCode.NO_USER_PREFERENCES_FOR_GIVEN_USER_ERROR));
        }
        return Optional.empty();
    }

    private int countAge(String dateOfBirthday) {
        Pattern pattern = Pattern.compile("\\.\\d{4}");
        Matcher matcher = pattern.matcher(dateOfBirthday);
        int userYearOfBirthday = Integer.parseInt(matcher.group().substring(1));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return userYearOfBirthday - year;
    }


}

package com.example.sp.userdisplay.control;

import com.example.sp.image.model.Image;
import com.example.sp.image.repository.ImageRepository;
import com.example.sp.user.model.User;
import com.example.sp.user.repository.UserRepository;
import com.example.sp.userdisplay.entity.UserDisplay;
import com.example.sp.userpreferences.model.UserPreferences;
import com.example.sp.userpreferences.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class UserDisplayRepository {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPreferencesRepository userPreferencesRepository;
    @Autowired
    ImageRepository imageRepository;

    public List<UserDisplay> getPotentialMatch(Long userId) {
        UserPreferences userPreferences = userPreferencesRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("UserPreferences should be present on this stage"));
        List<User> sortedPossibleMatches = userRepository.findAllByGender(userPreferences.getGender()).stream()
                .filter(user -> isPossibleUserToMatchInProperAge(user.getDateOfBirthday(), userPreferences.getBelowAge(), userPreferences.getUpperAge()))
                .sorted(Comparator.comparing(user -> doesUsersHaveSameInterests(user, userPreferences), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return getUserDisplays(sortedPossibleMatches);
    }

    private boolean isPossibleUserToMatchInProperAge(String dateOfBirthday, int belowAge, int upperAge) {
        int userAge = countAge(dateOfBirthday);
        return userAge >= belowAge && userAge <= upperAge;
    }

    private int countAge(String dateOfBirthday) {
        int userYearOfBirthday = Integer.parseInt(dateOfBirthday.substring(0, 4));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - userYearOfBirthday;
    }

    private boolean doesUsersHaveSameInterests(User user, UserPreferences userPreferences) {
        return userPreferences.getInterest().toLowerCase().equals(user.getInterests().toLowerCase());
    }

    private List<UserDisplay> getUserDisplays(List<User> users) {
        List<Long> userIds = users.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<Image> userImages = imageRepository.findAllById(userIds);
        Map<Long, byte[]> idToImage = userIds.stream()
                .collect(Collectors.toMap(Function.identity(), id -> getImage(id, userImages)));
        return users.stream()
                .map(user -> buildUserDisplay(user, idToImage))
                .collect(Collectors.toList());
    }

    private byte[] getImage(Long userId, List<Image> userImages) {
        return userImages.stream()
                .filter(image -> image.getUserId().equals(userId))
                .map(Image::getImage)
                .findFirst()
                .orElse(new byte[0]);
    }

    private UserDisplay buildUserDisplay(User user, Map<Long, byte[]> idToImage) {
        return UserDisplay.builder()
                .userId(user.getId())
                .name(user.getName())
                .age(countAge(user.getDateOfBirthday()))
                .description(user.getDescription())
                .interests(user.getInterests())
                .image(getBase64EncodedImageString(idToImage.get(user.getId())))
                .build();
    }

    private String getBase64EncodedImageString(byte[] image) {
        if (image.length == 0) {
            return "";
        }
        return Base64.getEncoder().encodeToString(image);
    }
}

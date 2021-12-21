package com.example.sp.userdisplay.control;

import com.example.sp.image.control.ImageRepository;
import com.example.sp.image.entity.Image;
import com.example.sp.user.control.UserRepository;
import com.example.sp.user.entity.User;
import com.example.sp.userdisplay.entity.UserDisplay;
import com.example.sp.userpreferences.control.UserPreferencesRepository;
import com.example.sp.userpreferences.entity.UserPreferences;
import com.example.sp.userranking.UserRankingService;
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
    @Autowired
    UserRankingService userRankingService;

    public List<UserDisplay> getPotentialMatch(Long userId) {
        UserPreferences userPreferences = userPreferencesRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("UserPreferences should be present on this stage"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User should be present on this stage"));
        List<Long> likedUsersIds = getLikedUsersIds(user, userId);
        List<User> usersToDisplay = userRepository.findAllByGender(userPreferences.getGender()).stream()
                .filter(userToSort -> !likedUsersIds.contains(userToSort.getId()))
                .filter(userToSort -> !userToSort.getId().equals(userId))
                .filter(userToSort -> isPossibleUserToMatchInProperAge(userToSort.getDateOfBirthday(), userPreferences.getBelowAge(), userPreferences.getUpperAge()))
                .sorted(Comparator.comparing(userToSort -> userRankingService.getUserRanking(user, userToSort), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return getUserDisplays(usersToDisplay);
    }

    public List<UserDisplay> getMatches(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User should be present on this stage"));
        List<Long> likedUsersIds = getLikedUsersIds(user, userId);
        List<User> likedUsers = userRepository.findAllById(likedUsersIds);
        List<User> matches = likedUsers.stream()
                .filter(likedUser -> areUsersMatch(likedUser, userId))
                .collect(Collectors.toList());
        return getUserDisplays(matches);
    }

    private List<Long> getLikedUsersIds(User user, Long userId) {
        if (user.getLikedUserId() != null) {
            return Arrays.stream(user.getLikedUserId().split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private boolean areUsersMatch(User user, Long userId) {
        if (user.getLikedUserId() == null) {
            return false;
        }
        return user.getLikedUserId().contains(String.valueOf(userId));
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
        if (user.getInterests() == null) {
            return false;
        }
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

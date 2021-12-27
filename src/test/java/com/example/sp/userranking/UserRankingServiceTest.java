package com.example.sp.userranking;

import com.example.sp.user.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRankingServiceTest {

    private static final String FIRST_INTERESTS = "BOOKS,MOVIES,GYM";
    private static final String SECOND_INTERESTS = "MOVIES,CYCLING";
    private static final String THIRD_INTERESTS = "BOOKS,GYM";
    private static final String FOURTH_INTERESTS = "GRAB A DRINK,BOOKS";
    private static final String FIFTH_INTERESTS = "";
    private static final String ENTP = "ENTP";
    private static final String ENFP = "ENFP";
    private static final String ISFJ = "ISFJ";
    private static final String ISFP = "ISFP";

    private static UserRankingService service = new UserRankingService();

    @Test
    public void testCompatibilityMbtiAndTwoMatchingInterests() {
        User currentUser = buildUser(FIRST_INTERESTS, ENFP);
        User userToCount = buildUser(THIRD_INTERESTS, ENTP);

        int result = service.getUserRanking(currentUser, userToCount);

        assertEquals(4, result);
    }

    @Test
    public void testCompatibilityMbtiAndOneMatchingInterestsButIn() {
        User currentUser = buildUser(FIRST_INTERESTS, ENFP);
        User userToCount = buildUser(FOURTH_INTERESTS, ENTP);

        int result = service.getUserRanking(currentUser, userToCount);

        assertEquals(3, result);
    }

    @Test
    public void testNoCompatibilityMbtiAndNoMatchingInterestsButIn() {
        User currentUser = buildUser(SECOND_INTERESTS, ISFJ);
        User userToCount = buildUser(FOURTH_INTERESTS, ENFP);

        int result = service.getUserRanking(currentUser, userToCount);

        assertEquals(0, result);
    }

    @Test
    public void testCompatibilityMbtiAndEmptyInOneUserInterestsButIn() {
        User currentUser = buildUser(SECOND_INTERESTS, ENFP);
        User userToCount = buildUser(FIFTH_INTERESTS, ENFP);

        int result = service.getUserRanking(currentUser, userToCount);

        assertEquals(2, result);
    }

    @Test
    public void testHalfCompatibilityMbtiAndEmptyInOneUserInterestsButIn() {
        User currentUser = buildUser(SECOND_INTERESTS, ENTP);
        User userToCount = buildUser(FIFTH_INTERESTS, ISFP);

        int result = service.getUserRanking(currentUser, userToCount);

        assertEquals(1, result);
    }


    private User buildUser(String interests, String mbtiType) {
        return User.UserBuilder.builder()
                .mbtiType(mbtiType)
                .interests(interests)
                .build();
    }
}

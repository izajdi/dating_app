package com.example.sp.userranking;

import com.example.sp.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sp.userranking.MbtiTypeEnum.*;
import static java.util.Map.entry;

@Component
public class UserRankingService {
    private static Map<MbtiTypeEnum, List<MbtiTypeEnum>> MBTI_TYPE_TO_COMPATIBLES_TYPES = Map.ofEntries(
            entry(INFP, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(ENFP, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(INFJ, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(ENFJ, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP, ISFP)),
            entry(INTJ, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(ENTJ, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(INTP, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(ENTP, List.of(INFP, ENFP, ENFJ, INTJ, ENTJ, INTP, ENTP)),
            entry(ISFP, List.of(ESFJ, ESTJ)),
            entry(ESFP, List.of(ISFJ, ISTJ)),
            entry(ISTP, List.of(ESFJ, ESTJ)),
            entry(ESTP, List.of(ISFJ, ISTJ)),
            entry(ISFJ, List.of(ESFP, ESTP, ISFJ, ESFJ, ISTJ, ESTJ)),
            entry(ISTJ, List.of(ESFP, ESTP, ISFJ, ESFJ, ISTJ, ESTJ)),
            entry(ESFJ, List.of(ISFP, ISTP, ISFJ, ESFJ, ISTJ, ESTJ)),
            entry(ESTJ, List.of(INTP, ISFP, ISTP, ISFJ, ESFJ, ISTJ, ESTJ)));

    private static Map<String, MbtiTypeEnum> STRING_MBTI_TO_MBTI_TYPES = Map.ofEntries(
            entry("INFP", INFP),
            entry("ENFP", ENFP),
            entry("INFJ", INFJ),
            entry("ENFJ", ENFJ),
            entry("INTJ", INTJ),
            entry("ENTJ", ENTJ),
            entry("INTP", INTP),
            entry("ENTP", ENTP),
            entry("ISFP", ISFP),
            entry("ESFP", ESFP),
            entry("ISTP", ISTP),
            entry("ESTP", ESTP),
            entry("ISFJ", ISFJ),
            entry("ISTJ", ISTJ),
            entry("ESFJ", ESFJ),
            entry("ESTJ", ESTJ));

    public int getUserRanking(User currentUser, User userToCount) {
        MbtiTypeEnum currentUserMbti = mapToMbtiTypeEnum(currentUser.getMbtiType());
        MbtiTypeEnum userToCountMbti = mapToMbtiTypeEnum(userToCount.getMbtiType());
        Set<String> currentUserInterests = Arrays.stream(currentUser.getInterests().split(","))
                .collect(Collectors.toSet());
        Set<String> userToCountInterests = Arrays.stream(userToCount.getInterests().split(","))
                .collect(Collectors.toSet());
        return countMbtiCompatibility(currentUserMbti, userToCountMbti) +
                countInterestsCompatibility(currentUserInterests, userToCountInterests);
    }

    private int countMbtiCompatibility(MbtiTypeEnum currentUserMbti, MbtiTypeEnum userToCountMbti) {
        if (MBTI_TYPE_TO_COMPATIBLES_TYPES.get(currentUserMbti).contains(userToCountMbti)) {
            return 2;
        }
        return 0;
    }

    private int countInterestsCompatibility(Set<String> currentUserInterests, Set<String> userToCountInterests) {
        currentUserInterests.retainAll(userToCountInterests);
        return currentUserInterests.size();
    }

    private MbtiTypeEnum mapToMbtiTypeEnum(String mbtiType) {
        return STRING_MBTI_TO_MBTI_TYPES.get(mbtiType);
    }
}

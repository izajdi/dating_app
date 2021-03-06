package com.example.sp.userdisplay.boundary;

import com.example.sp.common.error.control.ErrorMapper;
import com.example.sp.common.error.entity.Error;
import com.example.sp.common.validators.user.UserExistenceValidator;
import com.example.sp.userdisplay.control.UserDisplayRepository;
import com.example.sp.userdisplay.entity.UserDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDisplayProxy {

    @Autowired
    UserExistenceValidator userExistenceValidator;
    @Autowired
    UserDisplayRepository userDisplayRepository;
    @Autowired
    ErrorMapper errorMapper;

    public ResponseEntity getPotentialMatches(Long userId) {
        Optional<Error> error = userExistenceValidator.validate(userId);
        if (error.isPresent()) {
            return errorMapper.mapToResponseEntity(error.get());
        }
        List<UserDisplay> potentialMatch = userDisplayRepository.getPotentialMatch(userId);
        return ResponseEntity.ok(potentialMatch);
    }

    public ResponseEntity getMatches(Long userId) {
        Optional<Error> error = userExistenceValidator.validate(userId);
        if (error.isPresent()) {
            return errorMapper.mapToResponseEntity(error.get());
        }
        List<UserDisplay> matches = userDisplayRepository.getMatches(userId);
        return ResponseEntity.ok(matches);
    }
}

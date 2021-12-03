package com.example.sp.common.validators.image;

import com.example.sp.common.error.model.Error;
import com.example.sp.common.error.model.ErrorCode;
import com.example.sp.image.control.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageExistenceValidator {

    @Autowired
    ImageRepository imageRepository;

    public Optional<Error> validate(Long userId) {
        if (!imageRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("No image assigned to user with id: %d", userId),
                    ErrorCode.USER_WITH_GIVEN_ID_HAVE_IMAGE_ALREADY_ASSIGNED_ERROR));
        }
        return Optional.empty();
    }
}

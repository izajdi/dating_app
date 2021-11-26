package com.example.sp.image.control;

import com.example.sp.error.model.Error;
import com.example.sp.error.model.ErrorCode;
import com.example.sp.image.model.Image;
import com.example.sp.image.repository.ImageRepository;
import com.example.sp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    public Optional<Error> validateForSave(Long userId) {
        if (!userRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("User with given id: %d does not exist in db, so uplouding image is impossible", userId),
                    ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR));
        }
        if (imageRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("POST method not allowed for existed images for user with id: %d", userId),
                    ErrorCode.USER_WITH_GIVEN_ID_HAVE_IMAGE_ALREADY_ASSIGNED_ERROR));
        }
        return Optional.empty();
    }

    public Optional<Error> validateForRender(Long userId) {
        if (!userRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("User with given id: %d does not exist in db, so uplouding image is impossible", userId),
                    ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR));
        }
        if (!imageRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("No image assigned to user with id: %d", userId),
                    ErrorCode.USER_WITH_GIVEN_ID_HAVE_IMAGE_ALREADY_ASSIGNED_ERROR));
        }
        return Optional.empty();
    }

    public Optional<Error> validateForUpload(Long userId) {
        if (!userRepository.existsById(userId)) {
            return Optional.of(new Error(String.format("User with given id: %d does not exist in db, so uplouding image is impossible", userId),
                    ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR));
        }
        return Optional.empty();
    }

    @Transactional
    public void save(Long userId, MultipartFile file) {
        try {
            Image image = buildImage(userId, file.getBytes());
            imageRepository.save(image);
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong during converting image to byte array");
        }
    }

    @Transactional
    public byte[] get(Long userId) {
        Image image = imageRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Image should be present on this stage"));
        if (image.getImage() != null) {
            return image.getImage();
        }
        return new byte[0];
    }

    @Transactional
    public void upload(Long userId, MultipartFile file) {
        Optional<Image> image = imageRepository.findById(userId);
        if (image.isEmpty()) {
            save(userId, file);
            return;
        }
        try {
            byte[] byteImage = file.getBytes();
            image.get().setImage(byteImage);
            imageRepository.save(image.get());
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong during converting image to byte array");
        }
    }

    private Image buildImage(Long userId, byte[] byteImage) {
        return Image.builder()
                .userId(userId)
                .image(byteImage)
                .build();
    }
}

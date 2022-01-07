package com.example.sp.image.control;

import com.example.sp.common.error.entity.Error;
import com.example.sp.common.validators.image.ImageExistenceValidator;
import com.example.sp.common.validators.user.UserExistenceValidator;
import com.example.sp.image.entity.Image;
import com.example.sp.user.control.UserRepository;
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
    @Autowired
    UserExistenceValidator userExistenceValidator;
    @Autowired
    ImageExistenceValidator imageExistenceValidator;


    public Optional<Error> validateForRender(Long userId) {
        Optional<Error> userExistenceError = userExistenceValidator.validate(userId);
        if (userExistenceError.isPresent()) {
            return userExistenceError;
        }
        return imageExistenceValidator.validate(userId);
    }

    public Optional<Error> validateForUpload(Long userId) {
        return userExistenceValidator.validate(userId);
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

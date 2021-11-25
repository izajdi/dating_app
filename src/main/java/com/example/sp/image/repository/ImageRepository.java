package com.example.sp.image.repository;

import com.example.sp.User.model.User;
import com.example.sp.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Repository
public class ImageRepository {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveImageFile(Long userId, MultipartFile file) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with given id is not present in db"));
            byte[] byteImage = file.getBytes();
            user.setImage(byteImage);
            userRepository.save(user);
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong during uplouding photo");
        }
    }

    @Transactional
    public byte[] getImage(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            if (user.get().getImage() != null) {
                return user.get().getImage();
            }
        }
        return new byte[0];
    }
}

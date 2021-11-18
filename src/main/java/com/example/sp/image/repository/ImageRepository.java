package com.example.sp.image.repository;

import com.example.sp.User.model.User;
import com.example.sp.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Repository
public class ImageRepository {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveImageFile(Long userId, MultipartFile file) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with given id is not present in db"));
            Byte[] byteImage = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteImage[i++] = b;
            }
            userRepository.save(user);
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong during uplouding photo");
        }
    }
}

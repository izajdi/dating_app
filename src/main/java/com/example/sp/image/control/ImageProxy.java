package com.example.sp.image.control;

import com.example.sp.error.control.ErrorMapper;
import com.example.sp.error.model.Error;
import com.example.sp.image.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

@Repository
public class ImageProxy {

    @Autowired
    ImageService imageService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ErrorMapper errorMapper;

    public ResponseEntity saveImage(Long userId, MultipartFile file) {
        Optional<Error> error = imageService.validateForSave(userId);
        if (error.isPresent()) {
            return errorMapper.mapToResponseEntity(error.get());
        }
        imageService.save(userId, file);
        return ResponseEntity.ok(String.format("Image was saved for user with id: %d", userId));
    }

    public void renderImage(Long userId, HttpServletResponse response) throws Exception {
        Optional<Error> error = imageService.validateForRender(userId);
        if (error.isPresent()) {
            errorMapper.mapToResponseEntity(error.get(), response);
            return;
        }
        byte[] userImage = imageService.get(userId);
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(userImage);
        IOUtils.copy(is, response.getOutputStream());
    }
}

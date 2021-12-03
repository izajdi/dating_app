package com.example.sp.image.boundary;

import com.example.sp.common.error.control.ErrorMapper;
import com.example.sp.common.error.model.Error;
import com.example.sp.image.control.ImageService;
import com.example.sp.image.control.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

@Component
public class ImageProxy {

    @Autowired
    ImageService imageService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ErrorMapper errorMapper;


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

    public ResponseEntity uploadImage(Long userId, MultipartFile file) {
        Optional<Error> error = imageService.validateForUpload(userId);
        if (error.isPresent()) {
            return errorMapper.mapToResponseEntity(error.get());
        }
        imageService.upload(userId, file);
        return ResponseEntity.ok(String.format("Image was uploaded for user with id: %d", userId));
    }
}

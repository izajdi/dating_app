package com.example.sp.error.control;

import com.example.sp.error.model.Error;
import com.example.sp.error.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
public class ErrorMapper {

    private static Map<ErrorCode, HttpStatus> errorCodeToHttpCode = Map.of(ErrorCode.USER_WITH_GIVEN_ID_HAVE_IMAGE_ALREADY_ASSIGNED_ERROR, HttpStatus.BAD_REQUEST,
            ErrorCode.NO_USER_WITH_GIVEN_ID_ERROR, HttpStatus.METHOD_NOT_ALLOWED, ErrorCode.NO_IMAGE_FOR_GIVEN_USER, HttpStatus.BAD_REQUEST,
            ErrorCode.NO_USER_PREFERENCES_FOR_GIVEN_USER_ERROR, HttpStatus.BAD_REQUEST);

    public ResponseEntity<String> mapToResponseEntity(Error error) {
        HttpStatus httpStatus = errorCodeToHttpCode.getOrDefault(error.getErrorCode(), HttpStatus.BAD_REQUEST);
        return ResponseEntity
                .status(httpStatus.value())
                .body(error.getMessage());
    }

    public void mapToResponseEntity(Error error, HttpServletResponse response) throws IOException {
        HttpStatus httpStatus = errorCodeToHttpCode.getOrDefault(error.getErrorCode(), HttpStatus.BAD_REQUEST);
        response.setContentType("text/plain");
        response.setStatus(httpStatus.value());
        response.getWriter().write(error.getMessage());
    }
}

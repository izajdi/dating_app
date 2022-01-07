package com.example.sp.common.error.entity;

import java.util.Objects;

public class Error {
    private final String message;
    private final ErrorCode errorCode;

    public Error(String message, ErrorCode errorCode) {
        this.message = Objects.requireNonNull(message);
        this.errorCode = Objects.requireNonNull(errorCode);
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

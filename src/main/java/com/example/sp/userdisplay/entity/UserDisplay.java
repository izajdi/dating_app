package com.example.sp.userdisplay.entity;

import java.util.Objects;

public class UserDisplay {

    private final Long userId;
    private final String name;
    private final int age;
    private final String description;
    private final String interests;
    private final byte[] image;

    private UserDisplay(Builder builder) {
        userId = Objects.requireNonNull(builder.userId);
        name = Objects.requireNonNull(builder.name);
        age = builder.age;
        description = Objects.requireNonNull(builder.description);
        interests = Objects.requireNonNull(builder.interests);
        image = Objects.requireNonNull(builder.image);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long userId;
        private String name;
        private int age;
        private String description;
        private String interests;
        private byte[] image;

        private Builder() {
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder interests(String interests) {
            this.interests = interests;
            return this;
        }

        public Builder image(byte[] image) {
            this.image = image;
            return this;
        }

        public UserDisplay build() {
            return new UserDisplay(this);
        }
    }
}

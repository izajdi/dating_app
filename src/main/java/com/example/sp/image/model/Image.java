package com.example.sp.image.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    private Image(Builder builder) {
        this.userId = Objects.requireNonNull(builder.userId);
        this.image = builder.image;
    }

    public Image() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public static final class Builder {
        private Long userId;
        private byte[] image;

        private Builder() {
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder image(byte[] image) {
            this.image = image;
            return this;
        }

        public Image build() {
            return new Image(this);
        }
    }
}

package com.example.sp.Photo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Lob
    @Column(name = "photo", columnDefinition = "BYTEA")
    private byte[] photo;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public static PhotoBuilder builder() {
        return new PhotoBuilder();
    }

    private Photo(PhotoBuilder builder) {
        userId = builder.userId;
        photo = builder.photo;
    }

    public static final class PhotoBuilder {
        private Long id;
        private Long userId;
        private byte[] photo;

        private PhotoBuilder() {
        }

        public PhotoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PhotoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public PhotoBuilder photo(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }
}

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
    @Column(name = "photo", columnDefinition = "BLOB")
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
}

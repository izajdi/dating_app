package com.example.sp.Photo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "value")
    private String value;

    public Long getUserId() {
        return userId;
    }

    public String getValue() {
        return value;
    }
}

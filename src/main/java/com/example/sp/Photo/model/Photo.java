package com.example.sp.Photo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getValue() {
        return value;
    }
}

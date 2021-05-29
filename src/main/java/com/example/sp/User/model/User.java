package com.example.sp.User.model;

import com.example.sp.Photo.model.Photo;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name ="date_of_birthday")
    private String dateOfBirthday;

    @NonNull
    @Column(name ="email",unique = true)
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name ="password")
    private String password;

    @OneToMany
    @JoinColumn(name = "photos")
    private List<Photo> photos;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public List<Photo> getPhotos() {
        return photos;
    }


    public static final class UserBuilder {
        private Long id;
        private String name;
        private String dateOfBirthday;
        private String email;
        private String country;
        private String city;
        private String description;
        private String password;
        private List<Photo> photos = Collections.emptyList();

        private UserBuilder() {
        }

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder dateOfBirthday(String dateOfBirthday) {
            this.dateOfBirthday = dateOfBirthday;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder country(String country) {
            this.country = country;
            return this;
        }

        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder photos(List<Photo> photos) {
            this.photos = photos;
            return this;
        }

        public User build() {
            User user = new User();
            user.password = this.password;
            user.dateOfBirthday = this.dateOfBirthday;
            user.name = this.name;
            user.description = this.description;
            user.city = this.city;
            user.email = this.email;
            user.id = this.id;
            user.country = this.country;
            user.photos = this.photos;
            return user;
        }
    }
}

package com.example.sp.user.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birthday")
    private String dateOfBirthday;

    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "password")
    private String password;

    @Column(name = "interests")
    private String interests;

    @Column(name = "likedUserId")
    private String likedUserId;

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

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public String getInterests() {
        return interests;
    }

    public String getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(String id) {
        if (likedUserId == null) {
            likedUserId = id;
        } else {
            this.likedUserId = String.format("%s, %s", likedUserId, id);
        }
    }

    public static final class UserBuilder {
        private Long id;
        private String name;
        private String dateOfBirthday;
        private String email;
        private String country;
        private String city;
        private String gender;
        private String description;
        private String password;
        private String interests;
        private String likedUserId;

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

        public UserBuilder gender(String gender) {
            this.gender = gender;
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

        public UserBuilder interests(String interests) {
            this.interests = interests;
            return this;
        }

        public UserBuilder likedUserId(String likedUserId) {
            this.likedUserId = likedUserId;
            return this;
        }

        public User build() {
            User user = new User();
            user.password = this.password;
            user.dateOfBirthday = this.dateOfBirthday;
            user.name = this.name;
            user.description = this.description;
            user.city = this.city;
            user.gender = this.gender;
            user.email = this.email;
            user.id = this.id;
            user.country = this.country;
            user.interests = this.interests;
            user.likedUserId = this.likedUserId;
            return user;
        }
    }
}

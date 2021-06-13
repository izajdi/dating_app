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


    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name ="password")
    private String password;

    @OneToOne
    @JoinColumn(name = "photo", referencedColumnName = "user_id")
    private Photo photo;

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

    public String getGender() {return gender;}

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public Photo getPhoto() {
        return photo;
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
        private Photo photo;

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

        public UserBuilder photo(Photo photo) {
            this.photo = photo;
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
            user.photo = this.photo;
            return user;
        }
    }
}

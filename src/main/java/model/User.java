package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name ="age")
    private Integer age;

    public User() {

    }

    public User(String firstName, String lastName, String email, String description, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAge() {
        return age;
    }
}

package com.example.sp.userpreferences.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "below_age")
    private int belowAge;

    @Column(name = "upper_age")
    private int upperAge;

    @Column(name = "interest")
    private String interest;

    public Long getUserId() {
        return userId;
    }

    public String getGender() {
        return gender;
    }

    public int getBelowAge() {
        return belowAge;
    }

    public int getUpperAge() {
        return upperAge;
    }

    public String getInterest() {
        return interest;
    }
}

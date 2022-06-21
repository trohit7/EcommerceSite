package com.example.btechproject.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="user_profile")
public class UserProfile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;


    public UserProfile(Integer id, String username, String email, String firstName, String lastName) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}

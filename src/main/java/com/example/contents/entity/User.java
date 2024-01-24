package com.example.contents.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Target;

@Getter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    @Setter
    private String phone;
    @Setter
    private String bio;
    @Setter
    private String avatar;

    public User(String username, String email, String phone, String bio) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
    }
}

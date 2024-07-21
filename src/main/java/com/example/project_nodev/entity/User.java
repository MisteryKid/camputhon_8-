package com.example.project_nodev.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userid;

    private String username;

    private String password;

    private String email;
}

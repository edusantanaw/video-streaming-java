package com.edusantanaw.user.infra.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "user_tb")
@Table(name = "user_tb")
public class UserEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    @Column(nullable = false)
    protected String name;
    @Column(unique = true)
    protected  String email;
    @Column(nullable = false)
    protected  String password;

    public UserEntity() {
    }

    public UserEntity(String name, String email) {
    }

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

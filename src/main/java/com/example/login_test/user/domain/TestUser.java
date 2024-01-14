package com.example.login_test.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TestUser {

    @Id
    @GeneratedValue
    @Column(name = "test_user_id")
    private Long id;

    private String name;
    private String email;

    public static TestUser of(String name, String email) {
        TestUser user = new TestUser();
        user.name = name;
        user.email = email;
        return user;
    }
}

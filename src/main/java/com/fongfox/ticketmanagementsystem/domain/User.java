package com.fongfox.ticketmanagementsystem.domain;

import com.fongfox.ticketmanagementsystem.domain.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * User
 * <p>
 * Entity for user (version 0.1)
 * <p>
 * Version 1.0
 * Date: 7/8/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 7/8/2025      FongFox      Create user entity (ver 0.1)
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string_id", unique = true)
    private String stringId; //giả sử trường hợp có lưu trên NoSQL như mongoDb ...

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private UserStatus status = UserStatus.ACTIVE; //0: banned, 1: active; 2: deleted

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(Long id, String stringId, String firstName, String lastName, String email, String password, String avatarUrl, String phone, LocalDateTime emailVerifiedAt, LocalDateTime lastLoginAt, UserStatus status) {
        this.id = id;
        this.stringId = stringId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.emailVerifiedAt = emailVerifiedAt;
        this.lastLoginAt = lastLoginAt;
        this.status = status;
    }
}

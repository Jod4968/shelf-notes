package com.shelfnotes.entity;

import com.shelfnotes.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder

    @Entity
    @Table(name = "users")
    public class User extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Username is required")
        @Column(nullable = false, unique = true, length = 30)
        private String username;

        @NotBlank(message = "Name is required")
        @Column(nullable = false, length = 100)
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        @Column(nullable = false, unique = true, length = 100)
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Column(nullable = false)
        private String password;

        @Column(length = 255)
        private String profilePicturePath;

        @Column(length = 50)
        private String bio;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role Trole;

        @OneToMany(
                mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true
        )
        private List<Category> categories = new ArrayList<>();
    }


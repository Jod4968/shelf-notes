package com.shelfnotes.mapper;

import com.shelfnotes.dto.request.UserRegisterRequestDTO;
import com.shelfnotes.dto.response.UserResponseDTO;
import com.shelfnotes.entity.User;

public class UserMapper {

    private UserMapper() {
        // Prevent instantiation
    }

    /**
     * Converts UserRegisterRequestDTO to User Entity
     */
    public static User toEntity(UserRegisterRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return User.builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // Password will be encoded in Service layer
                .build();
    }

    /**
     * Converts User Entity to UserResponseDTO
     */
    public static UserResponseDTO toResponse(User user) {

        if (user == null) {
            return null;
        }

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .profilePicturePath(user.getProfilePicturePath())
                .bio(user.getBio())
                .role(user.getRole())
                .build();
    }
}
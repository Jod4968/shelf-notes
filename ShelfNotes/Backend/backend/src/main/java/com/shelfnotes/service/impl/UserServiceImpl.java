package com.shelfnotes.service.impl;

import com.shelfnotes.dto.request.UserRegisterRequestDTO;
import com.shelfnotes.dto.response.UserResponseDTO;
import com.shelfnotes.entity.User;
import com.shelfnotes.enums.Role;
import com.shelfnotes.exception.EmailAlreadyExistsException;
import com.shelfnotes.exception.UsernameAlreadyExistsException;
import com.shelfnotes.mapper.UserMapper;
import com.shelfnotes.repository.UserRepository;
import com.shelfnotes.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserResponseDTO register(UserRegisterRequestDTO requestDTO) {

        // Check if username already exists
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        // Convert DTO to Entity
        User user = UserMapper.toEntity(requestDTO);

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        user.setRole(Role.USER);

        // Save user
        User savedUser = userRepository.save(user);

        // Convert Entity to ResponseDTO
        return UserMapper.toResponse(savedUser);
    }
}
package com.shelfnotes.service.impl;

import com.shelfnotes.dto.request.LoginRequestDTO;
import com.shelfnotes.dto.request.UserRegisterRequestDTO;
import com.shelfnotes.dto.response.AuthResponseDTO;
import com.shelfnotes.dto.response.UserResponseDTO;
import com.shelfnotes.entity.User;
import com.shelfnotes.enums.Role;
import com.shelfnotes.exception.EmailAlreadyExistsException;
import com.shelfnotes.exception.InvalidCredentialsException;
import com.shelfnotes.exception.UsernameAlreadyExistsException;
import com.shelfnotes.mapper.UserMapper;
import com.shelfnotes.repository.UserRepository;
import com.shelfnotes.security.JwtService;
import com.shelfnotes.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponseDTO register(UserRegisterRequestDTO requestDTO) {

        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        User user = UserMapper.toEntity(requestDTO);

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO requestDTO) {

        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                requestDTO.getPassword(),
                user.getPassword()
        )) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }
}
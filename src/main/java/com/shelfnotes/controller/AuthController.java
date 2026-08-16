package com.shelfnotes.controller;

import com.shelfnotes.dto.request.LoginRequestDTO;
import com.shelfnotes.dto.request.UserRegisterRequestDTO;
import com.shelfnotes.dto.response.AuthResponseDTO;
import com.shelfnotes.dto.response.UserResponseDTO;
import com.shelfnotes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRegisterRequestDTO requestDTO) {

        UserResponseDTO response = userService.register(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO requestDTO) {

        AuthResponseDTO response = userService.login(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
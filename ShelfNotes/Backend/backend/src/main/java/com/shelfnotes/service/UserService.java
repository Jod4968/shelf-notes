package com.shelfnotes.service;

import com.shelfnotes.dto.request.LoginRequestDTO;
import com.shelfnotes.dto.request.UserRegisterRequestDTO;
import com.shelfnotes.dto.response.AuthResponseDTO;
import com.shelfnotes.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO register(UserRegisterRequestDTO requestDTO);

    AuthResponseDTO login(LoginRequestDTO requestDTO);
}
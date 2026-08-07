package com.shelfnotes.service;

import com.shelfnotes.dto.request.UserRegisterRequestDTO;
import com.shelfnotes.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO register(UserRegisterRequestDTO requestDTO);

}
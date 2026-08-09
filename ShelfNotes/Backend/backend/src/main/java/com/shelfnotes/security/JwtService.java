package com.shelfnotes.security;

import com.shelfnotes.entity.User;

public interface JwtService {

    String generateToken(User user);

}
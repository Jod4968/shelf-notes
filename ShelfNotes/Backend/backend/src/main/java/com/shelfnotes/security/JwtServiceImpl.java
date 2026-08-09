package com.shelfnotes.security;

import com.shelfnotes.entity.User;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public String generateToken(User user) {

        return null;
    }
}
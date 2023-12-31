package com.example.hangdang.dto;

import com.example.hangdang.entity.UserEntity;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String username;
    private final String token;

    public LoginResponseDto(String token, String username) {
        this.username = username;
        this.token = token;
    }

    public LoginResponseDto(UserEntity user) {
        this.username = user.getUsername();
        this.token = getToken();
    }
}



package com.gmail.tvmj.marcosvilchez.springbootShop.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
}

package com.gmail.tvmj.marcosvilchez.springbootShop.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String jwt;

}

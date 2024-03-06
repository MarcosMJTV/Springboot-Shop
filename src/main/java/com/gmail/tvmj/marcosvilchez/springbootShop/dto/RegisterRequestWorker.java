package com.gmail.tvmj.marcosvilchez.springbootShop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestWorker {
    String username;
    String password;
    String firstname;
    String lastname;
    int rol;
    String department;
}

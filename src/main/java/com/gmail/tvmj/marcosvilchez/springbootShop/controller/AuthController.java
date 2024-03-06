package com.gmail.tvmj.marcosvilchez.springbootShop.controller;


import com.gmail.tvmj.marcosvilchez.springbootShop.dto.AuthRequest;
import com.gmail.tvmj.marcosvilchez.springbootShop.dto.AuthResponse;
import com.gmail.tvmj.marcosvilchez.springbootShop.dto.RegisterRequestClient;
import com.gmail.tvmj.marcosvilchez.springbootShop.dto.RegisterRequestWorker;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService aService;

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest){
        return ResponseEntity.ok(aService.login(authRequest));
    }

    @PreAuthorize("permitAll")
    @PostMapping("/registerClient")
    ResponseEntity<String> registerClient(@RequestBody RegisterRequestClient request){
        return ResponseEntity.ok(aService.registerClient(request));
    }

    @PreAuthorize("permitAll")
    @PostMapping("/registerWorker")
    ResponseEntity<String> registerWorker(@RequestBody RegisterRequestWorker request){
        return ResponseEntity.ok(aService.registerWorker(request));
    }

    @PreAuthorize("permitAll")
    @GetMapping("/test")
    String accesesEndPoint(){
        return "este endpoint es publico";
    }
}

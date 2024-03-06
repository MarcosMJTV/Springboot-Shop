package com.gmail.tvmj.marcosvilchez.springbootShop.service;

import com.gmail.tvmj.marcosvilchez.springbootShop.dto.AuthRequest;
import com.gmail.tvmj.marcosvilchez.springbootShop.dto.AuthResponse;
import com.gmail.tvmj.marcosvilchez.springbootShop.dto.RegisterRequestClient;
import com.gmail.tvmj.marcosvilchez.springbootShop.dto.RegisterRequestWorker;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Cliente;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Trabajador;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import com.gmail.tvmj.marcosvilchez.springbootShop.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired private AuthenticationManager mananger;
    @Autowired private UserService uService;
    @Autowired private JwtService jService;
    @Autowired private PasswordEncoder encoder;
    @Autowired private ClienteService cService;
    @Autowired private TrabService tService;

    public AuthResponse login(AuthRequest authRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        );
        mananger.authenticate(authToken);
        User user = uService.findByUsername(authRequest.getUsername()).orElseThrow(() -> new RuntimeException("no se encontro"));
        String jwt = jService.generateToken(user, generateExtraClaims(user));
        System.out.println("jwt: " + jwt);
        return new AuthResponse(jwt);
    }

    public String registerClient(RegisterRequestClient request){
        Cliente client = new Cliente(request.getFirstname(), request.getLastname(), request.getCountry(),
                request.getUsername(), encoder.encode(request.getPassword()), Role.CLIENT);
        cService.saveClient(client);
        return "Registro exitoso";
    }

    public String registerWorker(RegisterRequestWorker request){
        if(request.getRol() < 1 || request.getRol() > 3){
            return "Rol Invalido" + request.getRol();
        }
        Trabajador worker = new Trabajador(request.getFirstname(), request.getLastname(), request.getDepartment(),
                request.getUsername(),encoder.encode(request.getPassword()), tService.designateRole(request.getRol()));
        tService.saveWorker(worker);
        return "Registro exitoso";
    }

    public Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("rol", user.getRol());
        extraClaims.put("permissions", user.getAuthorities());
        return extraClaims;
    }


}

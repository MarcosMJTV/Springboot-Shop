package com.gmail.tvmj.marcosvilchez.springbootShop.service;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Object> extraClaims){
         String jwt = Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + (EXPIRATION_MINUTES * 60 * 1000))).
                setHeaderParam(Header.TYPE, Header.JWT_TYPE).signWith((Key) generateKey(), SignatureAlgorithm.HS256).
                compact();
        return jwt;
    }

    private Key generateKey(){
        byte[] secretKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretKey);
    }

    public String extractUsername(String jwt){
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }

}

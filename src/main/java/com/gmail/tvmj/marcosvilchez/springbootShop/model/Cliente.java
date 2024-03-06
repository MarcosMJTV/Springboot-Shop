package com.gmail.tvmj.marcosvilchez.springbootShop.model;

import com.gmail.tvmj.marcosvilchez.springbootShop.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "idUser")
@DiscriminatorValue("Cliente")
public class Cliente extends  User{
    private String country;


    public Cliente(String firstname,String lastname,String country, String username, String password){
        super(firstname, lastname,username, password);
        this.country = country;
    }

    public Cliente(String firstname, String lastname, String country, String username, String password, Role rol){
        super(firstname, lastname,username, password, rol);
        this.country = country;
    }

}

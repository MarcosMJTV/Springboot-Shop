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
@DiscriminatorValue("Trabajador")
public class Trabajador extends User{

    private String department;

    public Trabajador(String firstname,String lastname,String department, String userMail, String password, Role rol){
        super(firstname, lastname,userMail, password, rol);
        this.department = department;
    }

}

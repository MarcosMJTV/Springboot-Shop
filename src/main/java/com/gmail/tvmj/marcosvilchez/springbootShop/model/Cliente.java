package com.gmail.tvmj.marcosvilchez.springbootShop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@PrimaryKeyJoinColumn(name = "idUser")
@DiscriminatorValue("Cliente")
public class Cliente extends  User{
    private String country;

    public Cliente(String firstname,String lastname,String country, String userMail, String password){
        super(firstname, lastname,userMail, password);
        client();
        this.country = country;
    }
}

package com.gmail.tvmj.marcosvilchez.springbootShop.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"userMail"})})
public class Cliente {

    private @Id @GeneratedValue Integer idClient;

    private String firstname;
    private String lastname;
    private String country;
    @Column(nullable = false)
    private String userMail;
    private String password;

    public Cliente(String firstname,String lastname,String country, String userMail, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.userMail = userMail;
        this.password = password;
    }
}

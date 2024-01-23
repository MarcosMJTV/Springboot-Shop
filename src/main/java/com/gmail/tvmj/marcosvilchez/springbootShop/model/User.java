package com.gmail.tvmj.marcosvilchez.springbootShop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userMail"})})
@DiscriminatorColumn(name = "user_type")
public abstract class User {
    private @NonNull @Id @GeneratedValue Integer idUser;
    private String firstname;
    private String lastname;
    private String userMail;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rol;

    public  User(String firstname, String lastname, String userMail, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.userMail = userMail;
        this.password = password;
    }

    public  User(String firstname, String lastname, String userMail, String password, Role rol){
        this.firstname = firstname;
        this.lastname = lastname;
        this.userMail = userMail;
        this.password = password;
        this.rol = rol;
    }

    public void client(){
        this.rol = Role.CLIENT;
    }
}

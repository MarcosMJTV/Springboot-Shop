package com.gmail.tvmj.marcosvilchez.springbootShop.model;

import com.gmail.tvmj.marcosvilchez.springbootShop.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@DiscriminatorColumn(name = "user_type")
public class User implements UserDetails {
    private @Id @GeneratedValue Integer idUser;
    private String firstname;
    private String lastname;
   @Column(nullable = false)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rol;


    public  User(String firstname, String lastname, String username, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public  User(String firstname, String lastname, String username, String password, Role rol){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public void client(){
        this.rol = Role.CLIENT;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = rol.getPermissions().stream().map(permission ->
                new SimpleGrantedAuthority(permission.name())).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.name()));
        return authorities;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

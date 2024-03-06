package com.gmail.tvmj.marcosvilchez.springbootShop.reporitory;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.Trabajador;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadorRepo extends JpaRepository<Trabajador, Integer> {
    public Trabajador findByUsername(String username);
}

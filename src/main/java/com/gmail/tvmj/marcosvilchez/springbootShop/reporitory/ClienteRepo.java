package com.gmail.tvmj.marcosvilchez.springbootShop.reporitory;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
}

package com.gmail.tvmj.marcosvilchez.springbootShop.reporitory;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepo extends JpaRepository<Producto, Integer> {

}

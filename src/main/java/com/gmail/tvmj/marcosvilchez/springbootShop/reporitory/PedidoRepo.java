package com.gmail.tvmj.marcosvilchez.springbootShop.reporitory;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {

}

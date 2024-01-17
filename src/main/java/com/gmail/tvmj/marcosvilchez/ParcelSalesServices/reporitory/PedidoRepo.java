package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.reporitory;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Pedido;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {

}

package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.reporitory;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
}

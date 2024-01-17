package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Pedido {

    private @NonNull @Id @GeneratedValue Integer idOrder;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente client;

    @ElementCollection
    @CollectionTable(name = "pedido_productos", joinColumns = @JoinColumn(name = "pedido_id"))
    @MapKeyJoinColumn(name = "producto_id")
    @Column(name = "cantidad")
    private Map<Producto, Integer> productos = new HashMap<>();

    private @NonNull double price;

    public Pedido(Cliente client){
        this.client = client;
    }
}

package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Producto {

    private @NonNull @Id @GeneratedValue Integer idProduct;
    private String name;
    private double price;
    private String description;

    public Producto(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

}

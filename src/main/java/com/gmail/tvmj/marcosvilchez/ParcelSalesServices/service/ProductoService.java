package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.service;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Producto;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.reporitory.ProductoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {
    private ProductoRepo repo;

    public Producto saveProduct(Producto product){
        return repo.save(product);
    }

    public Producto findProduct(Integer idProduct){
        return  repo.findById(idProduct).orElseThrow(() -> new RuntimeException("producto no encontrado"));
    }

    public List<Producto> findAll(){
        return repo.findAll();
    }


}

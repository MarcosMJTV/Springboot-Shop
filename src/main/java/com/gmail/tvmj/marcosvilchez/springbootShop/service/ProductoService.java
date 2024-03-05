package com.gmail.tvmj.marcosvilchez.springbootShop.service;

import com.gmail.tvmj.marcosvilchez.springbootShop.util.StatusProduct;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Producto;
import com.gmail.tvmj.marcosvilchez.springbootShop.reporitory.ProductoRepo;
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

    public Producto updateProduct(Producto productoOld, Integer idProduct){
        Producto newProduct = repo.findById(idProduct).map(producto ->{
            producto.setName(productoOld.getName());
            producto.setDescription(productoOld.getDescription());
            producto.setPrice(producto.getPrice());
            return repo.save(producto);
        }).orElseGet(() ->{
            productoOld.setIdProduct(idProduct);
            return repo.save(productoOld);
        });
        return newProduct;
    }
    public void delete(Integer idProduct){
        repo.deleteById(idProduct);
    }

    public Producto updateStatus(Producto product, StatusProduct status){
        product.setStatus(status);
        return repo.save(product);
    }
}

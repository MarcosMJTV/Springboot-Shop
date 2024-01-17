package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.service;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Pedido;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Producto;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.reporitory.PedidoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepo repo;

    public Pedido saveOrder(Pedido order){
        return repo.save(order);
    }

    public List<Pedido> findAll(){
        return repo.findAll();
    }

    public Pedido findOne(Integer idPedido){
        return repo.findById(idPedido).orElseThrow(() -> new RuntimeException("pedido no encontrado"));
    }

    public Pedido addList(Pedido pedido, Producto product){
        if(pedido.getProductos().containsKey(product)){
            pedido.getProductos().put(product,pedido.getProductos().get(product)+1);
        }else{
            pedido.getProductos().put(product,1);
        }
        repo.save(pedido);
        return pedido;
    }

    public Pedido deleteElementList(Pedido pedido, Producto product){
        if(pedido.getProductos().containsKey(product)) {
            int quantity = pedido.getProductos().get(product);
            if (quantity == 1) {
                pedido.getProductos().remove(product);
            } else {
                pedido.getProductos().put(product, pedido.getProductos().get(product) - 1);
            }
        }
        repo.save(pedido);
        return pedido;
    }
}

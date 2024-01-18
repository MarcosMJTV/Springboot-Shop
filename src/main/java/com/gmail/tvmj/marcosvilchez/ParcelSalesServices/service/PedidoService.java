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


    //use el key del map para registrar la cantidad de cada producto
    public Pedido addList(Pedido pedido, Producto product){
        if(pedido.getProductos().containsKey(product)){
            pedido.getProductos().put(product,pedido.getProductos().get(product)+1);
        }else{
            pedido.getProductos().put(product,1);
        }
        return repo.save(pedido);
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
        return repo.save(pedido);
    }

    public Pedido sumPrice(Pedido order, Producto product){
        double cost = order.getPrice();
        double sum = product.getPrice();
        cost = cost + sum;
        order.setPrice(cost);
        return repo.save(order);
    }

    public Pedido subPrice(Pedido order, Producto product){
        double cost = order.getPrice();
        double subt = product.getPrice();
        if (order.getProductos().containsKey(product)){
            System.out.println("entra");
            cost = cost - subt;

        }
        order.setPrice(cost);
        return repo.save(order);
    }
}

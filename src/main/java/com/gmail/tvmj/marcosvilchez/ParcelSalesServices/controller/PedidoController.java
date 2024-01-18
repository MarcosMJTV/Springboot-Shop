package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.controller;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Pedido;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Producto;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.service.PedidoService;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping("/compra")
public class PedidoController {

    private  ProductoService proService;
    private PedidoService pedService;
    private PedidoAssamble model;


    @PostMapping("/pedido")
    ResponseEntity<?> newOrder(@RequestBody Pedido order){
        EntityModel<Pedido> neworder = model.toModel(pedService.saveOrder(order));
        return ResponseEntity.created(neworder.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(neworder);
    }

    @GetMapping("/pedido")
    CollectionModel<EntityModel<Pedido>> findAll(){
        List<EntityModel<Pedido>> pedidos = pedService.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(pedidos, linkTo(methodOn(PedidoController.class).findAll()).withSelfRel());
    }

    @GetMapping("/pedido/{idPedido}")
    EntityModel<Pedido> findOne(@PathVariable Integer idPedido){
        Pedido order = pedService.findOne(idPedido);
        return model.toModel(order);
    }


    @PutMapping("/{idPedido}/{idProducto}")
    ResponseEntity<?> addProduct(@PathVariable Integer idProducto, @PathVariable Integer idPedido){
        Pedido order = pedService.findOne(idPedido);
        Producto product = proService.findProduct(idProducto);
        pedService.addList(order, product);
        pedService.sumPrice(order, product);
        EntityModel<Pedido> entityModel = model.toModel(order);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/borar/{idPedido}/{idProducto}")
    ResponseEntity<?> deleteElemetList(@PathVariable Integer idProducto, @PathVariable Integer idPedido){
        Pedido order = pedService.findOne(idPedido);
        Producto product = proService.findProduct(idProducto);
        pedService.subPrice(order, product);
        pedService.deleteElementList(order,product);
        EntityModel<Pedido> entityModel = model.toModel(order);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}

package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.controller;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Cliente;
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
@RequestMapping("/producto")
public class ProductoController {
    private ProductoService proService;
    private ProductoAssamble model;

    @PostMapping("/nuevo")
    ResponseEntity<?> newProducto(@RequestBody Producto product){
        EntityModel<Producto> newProduct = model.toModel(proService.saveProduct(product));
        return ResponseEntity.created(newProduct.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newProduct);
    }

    @GetMapping("/productos")
    CollectionModel<EntityModel<Producto>> findAll(){
        List<EntityModel<Producto>> products = proService.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductoController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{idProducto}")
    EntityModel<Producto> findOne(@PathVariable Integer idProducto){
        Producto product = proService.findProduct(idProducto);
        return model.toModel(product);
    }

}

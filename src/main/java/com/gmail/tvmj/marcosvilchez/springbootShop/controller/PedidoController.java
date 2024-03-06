package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.assemble.PedidoAssemble;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Pedido;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Producto;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.PedidoService;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.ProductoService;
import com.gmail.tvmj.marcosvilchez.springbootShop.util.StatusOrder;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private PedidoAssemble model;

    @PreAuthorize("PermitAll")
    @PostMapping("/pedido")
    public ResponseEntity<?> newOrder(@RequestBody Pedido order){
        EntityModel<Pedido> neworder = model.toModel(pedService.saveOrder(order));
        return ResponseEntity.created(neworder.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(neworder);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/pedido")
    public CollectionModel<EntityModel<Pedido>> findAll(){
        List<EntityModel<Pedido>> pedidos = pedService.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(pedidos, linkTo(methodOn(PedidoController.class).findAll()).withSelfRel());
    }

    @PreAuthorize("permitAll")
    @GetMapping("/pedido/{idPedido}")
    public EntityModel<Pedido> findOne(@PathVariable Integer idPedido){
        Pedido order = pedService.findOne(idPedido);
        return model.toModel(order);
    }

    @PreAuthorize("permitAll")
    @PutMapping("/{idPedido}/{idProducto}")
    public ResponseEntity<?> addProduct(@PathVariable Integer idProducto, @PathVariable Integer idPedido){
        Pedido order = pedService.findOne(idPedido);
        Producto product = proService.findProduct(idProducto);
        pedService.addList(order, product);
        pedService.sumPrice(order, product);
        EntityModel<Pedido> entityModel = model.toModel(order);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("permitAll")
    @PutMapping("/borar/{idPedido}/{idProducto}")
    public ResponseEntity<?> deleteElemetList(@PathVariable Integer idProducto, @PathVariable Integer idPedido){
        Pedido order = pedService.findOne(idPedido);
        Producto product = proService.findProduct(idProducto);
        pedService.subPrice(order, product);
        pedService.deleteElementList(order,product);
        EntityModel<Pedido> entityModel = model.toModel(order);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @PutMapping("/devuelto/{idOrden}")
    public ResponseEntity<?> returned(@PathVariable Integer idOrden){
        Pedido order = pedService.findOne(idOrden);
        if(order.getStatusOrder() == StatusOrder.IN_TRANSIT | order.getStatusOrder() == StatusOrder.DELIVERED){
            pedService.upStatus(order, StatusOrder.RETURNED);
            return ResponseEntity.ok(model.toModel(order));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.
                HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cambiar el estado a " +
                        "un pedido que no esté en pendiente"));
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @PutMapping("/entregado/{idOrden}")
    public ResponseEntity<?> delivered(@PathVariable Integer idOrden){
        Pedido order = pedService.findOne(idOrden);
        if(order.getStatusOrder() == StatusOrder.IN_TRANSIT){
            pedService.upStatus(order, StatusOrder.DELIVERED);
            return ResponseEntity.ok(model.toModel(order));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE,
                MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cambiar el estado " +
                        "a un pedido que no esté en pendiente"));
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @PutMapping("/enTransito/{idOrden}")
    public ResponseEntity<?> transit(@PathVariable Integer idOrden){
        Pedido order = pedService.findOne(idOrden);
        if(order.getStatusOrder() == StatusOrder.PENDING){
            pedService.upStatus(order, StatusOrder.IN_TRANSIT);
            return ResponseEntity.ok(model.toModel(order));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE,
                MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cambiar el estado " +
                        "a un pedido que no esté en pendiente"));
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @DeleteMapping("/cancelar/{idOrden}")
    public ResponseEntity<?> canceled(@PathVariable Integer idOrden){
        Pedido order = pedService.findOne(idOrden);
        if(order.getStatusOrder() == StatusOrder.IN_TRANSIT | order.getStatusOrder() == StatusOrder.PENDING){
            pedService.upStatus(order, StatusOrder.CANCELED);
            return ResponseEntity.ok(model.toModel(order));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE,
                MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cancelar un pedido que " +
                        "esté en el estado de: " + order.getStatusOrder()));
    }

    @PreAuthorize("hasAuthority('USER_ADMIN')")
    @DeleteMapping("/borar/{idOrden}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer idOrden){
        pedService.delete(idOrden);
        return ResponseEntity.noContent().build();
    }
}

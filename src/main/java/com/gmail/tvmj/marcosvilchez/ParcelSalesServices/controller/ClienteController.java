package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.controller;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Cliente;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Producto;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ClienteController {
    private final ClienteService service;
    private final ClienteAssamble model;

    @PostMapping("/cliente")
    ResponseEntity<?> newClient(@RequestBody Cliente client){
        EntityModel<Cliente> newclient = model.toModel(service.saveClient(client));
        return ResponseEntity.created(newclient.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newclient);
    }

    @GetMapping("/clientes")
    CollectionModel<EntityModel<Cliente>> findAll(){
        List<EntityModel<Cliente>> clientes = service.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(clientes, linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());
    }

    @GetMapping("/cliente/{idCliente}")
    EntityModel<Cliente> findOne(@PathVariable Integer idCliente){
        Cliente cliente = service.findOne(idCliente);
        return model.toModel(cliente);
    }

    @DeleteMapping("/cliente/borrar/{idCliente}")
    ResponseEntity<?> deleteClient(@PathVariable Integer idCliente){
        service.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cliente/actualizar/{idClient}")
    ResponseEntity<?> update(@RequestBody Cliente clientOld, @PathVariable Integer idClient){
        Cliente updateClient = service.update(clientOld, idClient);
        EntityModel<Cliente> entityModel = model.toModel(updateClient);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

}

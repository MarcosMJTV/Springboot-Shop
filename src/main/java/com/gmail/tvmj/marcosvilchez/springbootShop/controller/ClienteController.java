package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.assemble.ClienteAssemble;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Cliente;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired private ClienteService cService;
    @Autowired private ClienteAssemble model;

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @PostMapping("/cliente")
    public ResponseEntity<?> newClient(@RequestBody Cliente client){
        EntityModel<Cliente> newClient = model.toModel(cService.saveClient(client));
        return ResponseEntity.created(newClient.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newClient);
    }
    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @GetMapping("/clientes")
    public CollectionModel<EntityModel<Cliente>> findAll(){
        List<EntityModel<Cliente>> clientes = cService.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(clientes, linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @GetMapping("/cliente/{idCliente}")
    public EntityModel<Cliente> findOne(@PathVariable Integer idCliente){
        Cliente cliente = cService.findOne(idCliente);
        return model.toModel(cliente);
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @DeleteMapping("/cliente/borrar/{idCliente}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer idCliente){
        cService.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('USER_LEVEL3')")
    @PutMapping("/cliente/actualizar/{idClient}")
    public ResponseEntity<?> update(@RequestBody Cliente clientOld, @PathVariable Integer idClient){
        Cliente updateClient = cService.update(clientOld, idClient);
        EntityModel<Cliente> entityModel = model.toModel(updateClient);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("permitAll")
    @PostMapping("/test")
    public String welcome(){
        return "funciona esta verga";
    }
}

package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteAssemble implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClienteController.class).findOne(entity.getIdClient())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).findAll()).withRel("clientes"));
    }
}

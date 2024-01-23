package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.Trabajador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TrabajadorAssemble implements RepresentationModelAssembler<Trabajador, EntityModel<Trabajador>> {
    @Override
    public EntityModel<Trabajador> toModel(Trabajador entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(TrabajadorController.class).findOne(entity.getIdUser())).withSelfRel(),
                linkTo(methodOn(TrabajadorController.class).findAll()).withRel("Trabajadores"));
    }
}

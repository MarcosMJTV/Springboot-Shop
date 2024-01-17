package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.controller;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoAssamble implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @Override
    public EntityModel<Producto> toModel(Producto entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(ProductoController.class).findOne(entity.getIdProduct())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).findAll()).withRel("productos"));
    }
}

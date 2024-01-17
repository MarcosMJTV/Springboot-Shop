package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.controller;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoAssamble implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PedidoController.class).findOne(entity.getIdOrder())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).findAll()).withRel("compras"));
    }
}

package com.gmail.tvmj.marcosvilchez.springbootShop.assemble;

import com.gmail.tvmj.marcosvilchez.springbootShop.controller.PedidoController;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoAssemble implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(PedidoController.class).findOne(entity.getIdOrder())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).findAll()).withRel("compras"));
    }
}

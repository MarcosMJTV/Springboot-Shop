package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.assemble.TrabajadorAssemble;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Trabajador;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.TrabService;
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
@RequestMapping("/trabajador")
public class TrabajadorController {

    private TrabService trabService;
    private TrabajadorAssemble model;

    @PostMapping("/nuevo")
    public ResponseEntity<?> newWorker(@RequestBody Trabajador worker){
        EntityModel<Trabajador> newWorker = model.toModel(trabService.saveWorker(worker));
        return ResponseEntity.created(newWorker.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newWorker);
    }

    @GetMapping("/trabajadores")
    public CollectionModel<EntityModel<Trabajador>> findAll(){
        List<EntityModel<Trabajador>> worker = trabService.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(worker, linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());
    }

    @GetMapping("/trabajadores/{idTrabajador}")
    public EntityModel<Trabajador> findOne(@PathVariable Integer idTrabajador){
        Trabajador worker = trabService.findOne(idTrabajador);
        return model.toModel(worker);
    }

    @DeleteMapping("/trabajador/borrar/{idTrabajador}")
    public ResponseEntity<?> deleteWorker(@PathVariable Integer idTrabajador){
        trabService.delete(idTrabajador);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/trabajador/actualizar/{idTrabajador}")
    public ResponseEntity<?> update(@RequestBody Trabajador workerOld, @PathVariable Integer idTrabajador){
        Trabajador updateWorker = trabService.update(workerOld, idTrabajador);
        EntityModel<Trabajador> entityModel = model.toModel(updateWorker);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}

package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.assemble.ProductoAssemble;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Producto;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.ProductoService;
import com.gmail.tvmj.marcosvilchez.springbootShop.util.StatusProduct;
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
@RequestMapping("/producto")
public class ProductoController {
    private ProductoService proService;
    private ProductoAssemble model;

    @PreAuthorize("hasAuthority('USER_LEVEL2')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> newProducto(@RequestBody Producto product){
        EntityModel<Producto> newProduct = model.toModel(proService.saveProduct(product));
        return ResponseEntity.created(newProduct.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newProduct);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/productos")
    public CollectionModel<EntityModel<Producto>> findAll(){
        List<EntityModel<Producto>> products = proService.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductoController.class).findAll()).withSelfRel());
    }

    @PreAuthorize("permitAll")
    @GetMapping("/{idProducto}")
    public EntityModel<Producto> findOne(@PathVariable Integer idProducto){
        Producto product = proService.findProduct(idProducto);
        return model.toModel(product);
    }

    @PreAuthorize("hasAuthority('USER_LEVEL2')")
    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> updateProduct(@RequestBody Producto producto, @PathVariable Integer idProducto){
        Producto product = proService.updateProduct(producto, idProducto);
        EntityModel<Producto> entityModel = model.toModel(product);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("hasAuthority('USER_ADMIN')")
    @DeleteMapping("/borar/{idProducto}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer idProducto){
        proService.delete(idProducto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('USER_LEVEL2')")
    @PutMapping("/descontinuado/{idProducto}")
    public ResponseEntity<?> discontinued(@PathVariable Integer idProducto){
        Producto product = proService.findProduct(idProducto);
        if(product.getStatus() == StatusProduct.AVAILABLE | product.getStatus() == StatusProduct.OUT_OF_STOCK){
            proService.updateStatus(product, StatusProduct.DISCONTINUED);
            return ResponseEntity.ok(model.toModel(product));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cambiar el estado de este producto"));
    }

    @PreAuthorize("hasAuthority('USER_LEVEL2')")
    @PutMapping("/sinStock/{idProducto}")
    public ResponseEntity<?> nullStock(@PathVariable Integer idProducto){
        Producto product = proService.findProduct(idProducto);
        if(product.getStatus() == StatusProduct.AVAILABLE ){
            proService.updateStatus(product, StatusProduct.OUT_OF_STOCK);
            return ResponseEntity.ok(model.toModel(product));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cambiar el estado de este producto"));
    }

    @PreAuthorize("hasAuthority('USER_LEVEL2')")
    @PutMapping("/disponible/{idProducto}")
    public ResponseEntity<?> avialable(@PathVariable Integer idProducto){
        Producto product = proService.findProduct(idProducto);
        if(product.getStatus() == StatusProduct.OUT_OF_STOCK |  product.getStatus() == StatusProduct.DISCONTINUED){
            proService.updateStatus(product, StatusProduct.AVAILABLE);
            return ResponseEntity.ok(model.toModel(product));
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(
                Problem.create().withTitle("Método no permitido").withDetail("No puedes cambiar el estado de este producto"));
    }
}

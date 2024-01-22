package com.gmail.tvmj.marcosvilchez.springbootShop.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Integer idCliente){
            super("id: " + idCliente + " no encontrado");
        }
}

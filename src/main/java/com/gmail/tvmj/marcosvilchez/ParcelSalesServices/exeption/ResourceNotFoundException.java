package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.exeption;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Integer idCliente){
            super("id: " + idCliente + " no encontrado");
        }
}

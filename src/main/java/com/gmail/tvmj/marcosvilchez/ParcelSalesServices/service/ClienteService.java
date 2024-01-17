package com.gmail.tvmj.marcosvilchez.ParcelSalesServices.service;

import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.exeption.ResourceNotFoundException;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.model.Cliente;
import com.gmail.tvmj.marcosvilchez.ParcelSalesServices.reporitory.ClienteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepo repo;

    public Cliente saveClient(Cliente client){
        return repo.save(client);
    }

    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public Cliente findOne(Integer id){
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public Cliente update(Cliente clientOld, Integer id){
        Cliente client = repo.findById(id).map(cliente -> {
            cliente.setFirstname(clientOld.getFirstname());
            cliente.setLastname(clientOld.getLastname());
            cliente.setCountry(clientOld.getCountry());
            cliente.setUserMail(clientOld.getUserMail());
            cliente.setPassword(clientOld.getPassword());
            return repo.save(cliente);
        }).orElseGet(() -> {
            clientOld.setIdClient(id);
            return repo.save(clientOld);
        });
        return client;
    }
}

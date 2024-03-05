package com.gmail.tvmj.marcosvilchez.springbootShop.service;

import com.gmail.tvmj.marcosvilchez.springbootShop.exception.ResourceNotFoundException;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Cliente;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import com.gmail.tvmj.marcosvilchez.springbootShop.reporitory.ClienteRepo;
import com.gmail.tvmj.marcosvilchez.springbootShop.reporitory.UserRepo;
import com.gmail.tvmj.marcosvilchez.springbootShop.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepo repo;
    private final UserRepo uRepo;

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
            cliente.setUsername(clientOld.getUsername());
            cliente.setPassword(clientOld.getPassword());
            return repo.save(cliente);
        }).orElseGet(() -> {
            clientOld.setIdUser(id);
            return repo.save(clientOld);
        });
        return client;
    }
}

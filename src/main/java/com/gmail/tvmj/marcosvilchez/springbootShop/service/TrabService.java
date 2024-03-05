package com.gmail.tvmj.marcosvilchez.springbootShop.service;

import com.gmail.tvmj.marcosvilchez.springbootShop.exception.ResourceNotFoundException;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Trabajador;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import com.gmail.tvmj.marcosvilchez.springbootShop.reporitory.TrabajadorRepo;
import com.gmail.tvmj.marcosvilchez.springbootShop.reporitory.UserRepo;
import com.gmail.tvmj.marcosvilchez.springbootShop.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrabService {

    private TrabajadorRepo repo;
    private UserRepo uRepo;

    public Trabajador saveWorker (Trabajador worker){
        /*User user = new User(worker.getFirstname(), worker.getLastname(), worker.getUsername(), worker.getPassword(),
                worker.getRol());
        uRepo.save(user);*/
        return repo.save(worker);
    }

    public List<Trabajador> findAll(){
        return repo.findAll();
    }

    public Trabajador findOne(Integer idWorker){
        return repo.findById(idWorker).orElseThrow(() -> new ResourceNotFoundException(idWorker));
    }

    public void delete(Integer idWorker){
        repo.deleteById(idWorker);
    }

    public Trabajador update(Trabajador workerOld, Integer idWorker){
        Trabajador worker = repo.findById(idWorker).map(trabajador -> {
            trabajador.setFirstname(workerOld.getFirstname());
            trabajador.setLastname(workerOld.getLastname());
            trabajador.setDepartment(workerOld.getDepartment());
            trabajador.setUsername(workerOld.getUsername());
            trabajador.setPassword(workerOld.getPassword());
            trabajador.setRol(workerOld.getRol());
            return repo.save(trabajador);
        }).orElseGet(() -> {
            workerOld.setIdUser(idWorker);
            return repo.save(workerOld);
        });
        return worker;
    }


}

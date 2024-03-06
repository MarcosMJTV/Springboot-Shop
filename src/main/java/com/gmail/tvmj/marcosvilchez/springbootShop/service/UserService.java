package com.gmail.tvmj.marcosvilchez.springbootShop.service;

import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import com.gmail.tvmj.marcosvilchez.springbootShop.reporitory.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo repo;

    public Optional<User> findByUsername(String username){
        return repo.findByUsername(username);
    }

    public User saveUser(User user){
        return repo.save(user);
    }
}

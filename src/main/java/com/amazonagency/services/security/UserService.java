package com.amazonagency.services.security;

import com.amazonagency.model.security.User;
import com.amazonagency.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repository){
        this.repo = repository;
    }

    public User create (User user){

        if(repo.existsByUsername(user.getUsername()).isPresent()){
            throw new IllegalArgumentException("User with such username is already exist");
        }

        if(repo.existsByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with such email is already exist");
        }

        return repo.save(user);
    }

    public User getByUsername(String username){
        return repo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Unknown username"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

}

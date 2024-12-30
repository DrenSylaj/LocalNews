package com.localnews.user.services;

import com.localnews.user.Client.AnkesaClient;
import com.localnews.user.repositories.UserRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.localnews.user.entities.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final AnkesaClient ankesaClient;

    public void saveUser(User user){
        repository.save(user);
    }

    public Optional<User> findUserById(Integer id){
        return repository.findById(id);
    }

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public void deleteUser(Integer id) {

        User user  = findUserById(id).orElseThrow(
                () -> new NotFoundException("Nuk u gjete!")
        );

        repository.deleteById(id);
    }
}

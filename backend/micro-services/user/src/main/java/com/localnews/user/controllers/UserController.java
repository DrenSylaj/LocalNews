package com.localnews.user.controllers;

import com.localnews.user.Client.AnkesaClient;
import com.localnews.user.DTO.AnkesaDTO;
import com.localnews.user.entities.User;
import com.localnews.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final AnkesaClient  ankesaClient;

    @PostMapping
    public void saveUser(@RequestBody User user){
        service.saveUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Integer id){
        return service.findUserById(id).get();
    }

    @GetMapping("/ankesat/{userId}")
    public List<AnkesaDTO> getUserAnkesat(@PathVariable Integer userId) {
        return ankesaClient.getAnkesatByUserId(userId);
    }



}

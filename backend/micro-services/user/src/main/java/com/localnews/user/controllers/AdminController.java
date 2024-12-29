package com.localnews.user.controllers;

import com.localnews.user.entities.Autori;
import com.localnews.user.services.AutoriService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AutoriService autoriService;


    @PutMapping("/autor/{userId}")
    public ResponseEntity<String> updateRole(@PathVariable("userId") Integer userId, @RequestBody Autori autori){
        return autoriService.updateRole(userId, autori);
    }

}

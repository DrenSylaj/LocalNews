package com.localnews.user.controllers;

import com.localnews.user.DTO.AutoriDTO;
import com.localnews.user.entities.Autori;
import com.localnews.user.services.AutoriService;
<<<<<<< Updated upstream
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
=======
import lombok.RequiredArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autori")
@RequiredArgsConstructor
public class AutoriController {

    private final AutoriService autoriService;
    @GetMapping("/{userId}")
    Autori getAutoriByUserId(@PathVariable("user-id") Integer userId){
        return autoriService.findByUserId(userId);
    }

    @GetMapping
    AutoriDTO getAutoriByJwt(@RequestHeader("Authorization") String jwt){
        return autoriService.findByJwt(jwt);
    }
<<<<<<< Updated upstream

    @PermitAll
    @GetMapping("/secure")
    ResponseEntity<String> secured(){
        return ResponseEntity.ok("Hello from secured port");
    }
=======
>>>>>>> Stashed changes
}

package com.localnews.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AuthController {

    @GetMapping
    ResponseEntity<String> secure(){
        return ResponseEntity.ok("Hello from the admin secured port");
    }
}

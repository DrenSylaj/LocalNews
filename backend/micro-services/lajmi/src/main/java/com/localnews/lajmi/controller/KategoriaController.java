package com.localnews.lajmi.controller;

import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.service.KategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kategoria")
@RequiredArgsConstructor
public class KategoriaController {
    private final KategoriaService kategoriaService;


    @GetMapping
    public ResponseEntity<List<Kategoria>> getAllKategorite(){
        return ResponseEntity.ok(kategoriaService.allKategorite());
    }
}

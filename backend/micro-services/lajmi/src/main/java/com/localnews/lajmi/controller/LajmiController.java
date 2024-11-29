package com.localnews.lajmi.controller;

import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.service.LajmiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lajmet")
@RequiredArgsConstructor
public class LajmiController {

    private final LajmiService lajmiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody Lajmi lajmi
            ){
        lajmiService.saveLajmi(lajmi);
    }

    @GetMapping
    public ResponseEntity<List<Lajmi>> findAllLajmet(){
        return ResponseEntity.ok(lajmiService.findAllLajmet());
    }
}

package com.localnews.lajmi.controller;

import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.response.FullLajmiResponse;
import com.localnews.lajmi.service.LajmiService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @GetMapping("/{id}")
    public Lajmi findLajmiById(
            @PathVariable int id
    ){
        return lajmiService.findById(id);
    }

    @GetMapping("/with-comments/{lajmi-id}")
    public ResponseEntity<FullLajmiResponse> findLajmetWithComments(
            @PathVariable("lajmi-id") Integer lajmiId
    ){
        return ResponseEntity.ok(lajmiService.findLajmetWithComments(lajmiId));
    }

    @DeleteMapping("/{id}")
    public String deleteLajmi(@PathVariable int id) {
        return lajmiService.deleteLajmi(id);

    }
}

package com.LocalNews.Komenti.controller;

import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.service.KomentiService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/komentet")
@RequiredArgsConstructor
public class KomentiController {

    private final KomentiService service;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(
            @RequestBody Komenti komenti
            ) {
        service.createKomenti(komenti);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Integer id) {
        service.deleteKomenti(id);
    }

    @GetMapping
    public ResponseEntity<List<Komenti>> findAllKomentet() {
        return ResponseEntity.ok(service.findAllKomentet());
    }

    @PostMapping("/reply/{komenti_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void shtoReply(@PathVariable Integer komenti_id, @RequestBody Komenti reply) {
        service.shtoReply(komenti_id, reply);
    }

    @GetMapping("/replies/{parentId}")
    public ResponseEntity<List<Komenti>> getReplies(@PathVariable Integer parentId) {
        return ResponseEntity.ok(service.getReplies(parentId));
    }

    @GetMapping("/lajmi/{lajmi-id}")
    public ResponseEntity<List<Komenti>> findAllKomentetByLajmi(
            @PathVariable("lajmi-id") Integer lajmiId
    ) {
        return ResponseEntity.ok(service.findAllKomentetByLajmiId(lajmiId));
    }

}


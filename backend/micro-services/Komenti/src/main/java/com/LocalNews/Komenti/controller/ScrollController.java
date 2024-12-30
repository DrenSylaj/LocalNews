package com.LocalNews.Komenti.controller;

import com.LocalNews.Komenti.DTO.KomentiResponse;
import com.LocalNews.Komenti.client.UserClient;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.service.KomentiService;
import com.LocalNews.Komenti.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/komenti/noAuth")
@AllArgsConstructor
public class ScrollController {

    private final KomentiService service;

    @GetMapping("/scroll/{lajmiId}")
    public ResponseEntity<List<Komenti>> getComments(
            @PathVariable Integer lajmiId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        List<Komenti> comments = service.getPaginatedComments(lajmiId, page, size);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/scroll/replies/{komentiId}")
    public ResponseEntity<List<Komenti>> getReplies(
            @PathVariable Integer komentiId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        List<Komenti> comments = service.getReplies(komentiId, page, size);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Komenti> findKomentiById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findKomentiById(id));
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
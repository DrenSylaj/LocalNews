package com.LocalNews.Komenti.controller;

import com.LocalNews.Komenti.entity.Dislike;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.entity.Like;
import com.LocalNews.Komenti.service.KomentiService;
import com.LocalNews.Komenti.service.LikeService;
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
    private final LikeService likeService;

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

    @DeleteMapping("/komentet/{id}")
    public void deleteComments(@PathVariable Integer id) {
        service.deleteKomentetELajmit(id);
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

    @GetMapping("/{id}")
    public ResponseEntity<Komenti> findKomentiById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findKomentiById(id));
    }

    @GetMapping("/lajmi/{lajmi-id}")
    public ResponseEntity<List<Komenti>> findAllKomentetByLajmi(
            @PathVariable("lajmi-id") Integer lajmiId
    ) {
        return ResponseEntity.ok(service.findAllKomentetByLajmiId(lajmiId));
    }



    @PostMapping("/likes/{userId}/{commentId}/{isLike}")
    public ResponseEntity<Like> addLike(
            @PathVariable("userId") Integer userId,
            @PathVariable("commentId") Integer commentId,
            @PathVariable("isLike") boolean isLike
    ) {
        Like like = likeService.addLike(userId, commentId, isLike);

        return ResponseEntity.ok(like);
    }

    @PostMapping("/dislikes/{userId}/{commentId}/{isLike}")
    public ResponseEntity<Dislike> removeLike(
            @PathVariable("userId") Integer userId,
            @PathVariable("commentId") Integer commentId,
            @PathVariable("isLike") boolean isLike
    ) {
        Dislike dislike = likeService.addDislike(userId, commentId, isLike);

        return ResponseEntity.ok(dislike);
    }

    @DeleteMapping("/dislikes/{id}")
    public String removeDislike(@PathVariable("id") Integer id) {
        return likeService.deleteDislike(id);
    }

    @DeleteMapping("/likes/{id}")
    public String removeLike(@PathVariable("id") Integer id) {
        return likeService.deleteLike(id);
    }

    @GetMapping("/likes/{userId}")
    public ResponseEntity<List<Like>> getLikesByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(likeService.getLikesByUserId(userId));
    }

}


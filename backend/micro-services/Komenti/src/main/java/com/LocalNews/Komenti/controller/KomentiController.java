package com.LocalNews.Komenti.controller;

import com.LocalNews.Komenti.DTO.KomentiResponse;
import com.LocalNews.Komenti.DTO.ReplyRequest;
import com.LocalNews.Komenti.DTO.UserDTO;
import com.LocalNews.Komenti.client.UserClient;
import com.LocalNews.Komenti.entity.Dislike;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.entity.Like;
import com.LocalNews.Komenti.service.KomentiService;
import com.LocalNews.Komenti.service.LikeService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final UserClient userClient;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(
            @RequestBody Komenti komenti,
            @RequestHeader("Authorization") String token
            ) {

        if (komenti.getLajmiId() == null)
            throw new NullPointerException("LajmiId nuk munde te jete null!");

        if (komenti.getTeksti().trim().isBlank())
            throw new NullPointerException("Teksti nuk munde te jete bosh!");

        UserDTO user = userClient.getUserByJwt(token);

        komenti.setCreatorId(user.getId());

        service.createKomenti(komenti);
    }
    @DeleteMapping("/{id}")
    public String deleteKomenti(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer id) {

        UserDTO user = userClient.getUserByJwt(token);

        return service.deleteKomenti(id, user);
    }

    @PutMapping("/{id}")
    public Komenti updateKomenti(
            @RequestHeader("Authorization") String token,
            @RequestBody ReplyRequest request,
            @PathVariable Integer id) {

        UserDTO user = userClient.getUserByJwt(token);

        return service.updateKomenti(id, request.getTeksti() ,user);
    }

    // Kur shlyhet ni lajm i shlyn kejt komentet e atij lajmi.
    @DeleteMapping("/komentet/{id}")
    public void deleteComments(@PathVariable Integer id) {
        service.deleteKomentetELajmit(id);
    }

    @GetMapping
    public ResponseEntity<List<Komenti>> findAllKomentet() {
        return ResponseEntity.ok(service.findAllKomentet());
    }


    // Replies API !!!!
    @PostMapping("/reply/{komenti_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void shtoReply(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer komenti_id,
            @RequestBody ReplyRequest request) {

        UserDTO user = userClient.getUserByJwt(token);
        service.shtoReply(komenti_id, request.getTeksti(), user.getId());
    }





    // Like & Dislike API !!!
    @PostMapping("/like/{commentId}")
    public ResponseEntity<Like> addLike(
            @PathVariable("commentId") Integer commentId,
            @RequestHeader("Authorization") String token
    ) {
        UserDTO user = userClient.getUserByJwt(token);
        Like like = likeService.addLike(user.getId(), commentId);

        return ResponseEntity.ok(like);
    }

    @PostMapping("/dislike/{commentId}")
    public ResponseEntity<Dislike> removeLike(
            @PathVariable("commentId") Integer commentId,
            @RequestHeader("Authorization") String token
    ) {

        UserDTO user = userClient.getUserByJwt(token);

        Dislike dislike = likeService.addDislike(user.getId(), commentId);

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

    // Lajmi API !!



}


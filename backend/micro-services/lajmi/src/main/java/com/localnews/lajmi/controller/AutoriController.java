package com.localnews.lajmi.controller;

import com.localnews.lajmi.client.UserClient;
import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.response.AutoriDto;
import com.localnews.lajmi.service.LajmiService;
import com.localnews.lajmi.service.TagLajmiService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autori")
@RequiredArgsConstructor
public class AutoriController{

    private final LajmiService lajmiService;
    private final UserClient userClient;
    private final TagLajmiService tagLajmiService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody Lajmi lajmi,
            @RequestHeader("Authorization") String jwt
    ){
        try {
            System.out.println("Received JWT: " + jwt);
            AutoriDto autoriDto = userClient.autoriByJwt(jwt);
            System.out.println("AutoriDto: " + autoriDto);

            lajmi.setAutoriId(autoriDto.getId());
            lajmiService.saveLajmi(lajmi);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while saving Lajmi");
        }
    }

    @PostMapping("/tagLajmi/{tagId}/{lajmiId}")
    public void addTagToLajmi(@PathVariable Integer lajmiId, @PathVariable Integer tagId) {
        tagLajmiService.shtoTagLajmi(lajmiId, tagId);
    }

    @DeleteMapping("/tagLajmi/{tagId}/{lajmiId}")
    public ResponseEntity<Void> deleteTagFromLajmi(@PathVariable Integer lajmiId, @PathVariable Integer tagId) {
        tagLajmiService.removeTagFromLajmi(lajmiId, tagId);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<String> secured(){
        return ResponseEntity.ok("Hello from a secured port");
    }
}
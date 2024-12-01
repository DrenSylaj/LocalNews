package com.localnews.lajmi.controller;
import com.localnews.lajmi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.localnews.lajmi.entity.Tag;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTag(@RequestBody Tag tag){
        service.saveTag(tag);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        return ResponseEntity.ok(service.findAllTags());
    }

    @GetMapping("/{tag-id}")
    public Optional<Tag> getTagById(@PathVariable("tag-id") Integer tagId){
        return service.findTagById(tagId);
    }
}
package com.localnews.lajmi.controller;

import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.entity.Tag;
import com.localnews.lajmi.service.LajmiService;
import com.localnews.lajmi.service.TagLajmiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/taglajme/")
public class TagLajmiController {

    private final TagLajmiService tagLajmiService;





    @GetMapping("/{lajmiId}")
    public ResponseEntity<List<Tag>> getTagsOfLajmi(@PathVariable Integer lajmiId) {
        return ResponseEntity.ok(tagLajmiService.getTagsOfLajmi(lajmiId));
    }

    @GetMapping("/lajmet/{tagId}")
    public ResponseEntity<List<Lajmi>> getLajmetOfTags(@PathVariable Integer tagId) {
        return ResponseEntity.ok(tagLajmiService.getLajmetOfTag(tagId));
    }
}


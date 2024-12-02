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

    @PostMapping("/{lajmiId}/tags/{tagId}")
    public void addTagToLajmi(@PathVariable Integer lajmiId, @PathVariable Integer tagId) {
        tagLajmiService.shtoTagLajmi(lajmiId, tagId);
    }

    @DeleteMapping("/{lajmiId}/tags")
    public ResponseEntity<Void> deleteTagFromLajmi(@PathVariable Integer lajmiId, @PathVariable Integer tagId) {
        tagLajmiService.removeTagFromLajmi(lajmiId, tagId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{lajmiId}/tags")
    public ResponseEntity<List<Tag>> getTagsOfLajmi(@PathVariable Integer lajmiId) {
        return ResponseEntity.ok(tagLajmiService.getTagsOfLajmi(lajmiId));
    }

    @GetMapping("/lajmet/{tagId}")
    public ResponseEntity<List<Lajmi>> getLajmetOfTags(@PathVariable Integer tagId) {
        return ResponseEntity.ok(tagLajmiService.getLajmetOfTag(tagId));
    }
}


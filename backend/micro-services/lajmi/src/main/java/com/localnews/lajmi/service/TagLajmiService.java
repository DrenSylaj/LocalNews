package com.localnews.lajmi.service;

import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.entity.Tag;
import com.localnews.lajmi.entity.TagLajmi;
import com.localnews.lajmi.repository.LajmiRepository;
import com.localnews.lajmi.repository.TagLajmiRepository;
import com.localnews.lajmi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagLajmiService {

    private final TagLajmiRepository tagLajmiRepository;
    private final LajmiRepository lajmiRepository;
    private final TagRepository tagRepository;

    public void shtoTagLajmi(Integer lajmiId, Integer tagId) {
        Lajmi lajmi = lajmiRepository.findById(lajmiId)
                .orElseThrow(() -> new RuntimeException("Lajmi nuk u gjet!"));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag nuk u gjet!"));

        TagLajmi tagLajmi = TagLajmi.builder().lajmi(lajmi).tag(tag).build();

        tagLajmiRepository.save(tagLajmi);
    }

    public void removeTagFromLajmi(Integer lajmiId, Integer tagId) {
        TagLajmi tagLajmi = tagLajmiRepository.findByLajmiIdAndTagId(lajmiId, tagId)
                .orElseThrow(() -> new IllegalArgumentException("TagLajmi!"));

        tagLajmiRepository.delete(tagLajmi);
    }

    public List<Tag> getTagsOfLajmi(Integer lajmiId) {
        return tagLajmiRepository.findTagsByLajmiId(lajmiId);
    }

    public List<Lajmi> getLajmetOfTag(Integer tagId) {
        return tagLajmiRepository.findLajmeByTagId(tagId);
    }
}

package com.localnews.lajmi.service;

import com.localnews.lajmi.entity.Tag;
import com.localnews.lajmi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public Optional<Tag> findTagById(Integer id){
        return repository.findById(id);
    }

    public void saveTag(Tag tag){
        repository.save(tag);
    }

    public List<Tag> findAllTags(){
        return repository.findAll();
    }

}

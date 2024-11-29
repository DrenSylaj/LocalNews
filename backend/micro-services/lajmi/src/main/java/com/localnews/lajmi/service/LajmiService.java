package com.localnews.lajmi.service;

import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.repository.LajmiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LajmiService {

    private final LajmiRepository lajmiRepository;

    public void saveLajmi(Lajmi lajmi){
        lajmiRepository.save(lajmi);
    }

    public List<Lajmi> findAllLajmet(){
        return lajmiRepository.findAll();
    }

}

package com.localnews.lajmi.service;

import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.repository.KategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriaService {
    private final KategoriaRepository kategoriaRepository;

    public void saveKategoria(Kategoria kategoria){
        kategoriaRepository.save(kategoria);
    }

    public List<Kategoria> allKategorite(){
        return kategoriaRepository.findAll();
    }
}

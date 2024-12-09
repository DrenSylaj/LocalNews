package com.localnews.lajmi.service;

import com.localnews.lajmi.client.KomentiClient;
import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.repository.LajmiRepository;
import com.localnews.lajmi.response.FullLajmiResponse;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LajmiService {

    private final KomentiClient komentiClient;
    private final LajmiRepository lajmiRepository;

    private final TagLajmiService tagLajmiService;

    public void saveLajmi(Lajmi lajmi){
        lajmiRepository.save(lajmi);
    }

    public List<Lajmi> findAllLajmet(){
        return lajmiRepository.findAll();
    }

    public FullLajmiResponse findLajmetWithComments(Integer lajmiId) {
        var lajmi = lajmiRepository.findById(lajmiId)
                .orElseGet(() -> Lajmi.builder()
                        .teksti("NOT_FOUND")
                        .kategoria(Kategoria.builder()
                                .name("NOT FOUND")
                                .build())
                        .build());
        var komentet = komentiClient.findAllKomentetByLajmi(lajmiId);

        return FullLajmiResponse.builder()
                .teksti(lajmi.getTeksti())
                .kohaPublikimit(lajmi.getKohaPublikimit())
                .kategoria(lajmi.getKategoria())
                .komentet(komentet)
                .build();
    }

    @Cacheable(value = "lajme", key = "#id")
    public Lajmi findById(int id) {
        System.out.println("Service was Called to fetch through db()!");
        return lajmiRepository.findById(id).get();
    }

    @CacheEvict(value = "lajme", key = "#id")
    public String deleteLajmi(int id) {
        Lajmi lajmi = lajmiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lajmi nuk u gjete!"));

        tagLajmiService.removeTags(lajmi.getId());

        komentiClient.deleteComments(lajmi.getId());

        lajmiRepository.delete(lajmi);

        return "Lajmi u shlye me suskes!";
    }
}

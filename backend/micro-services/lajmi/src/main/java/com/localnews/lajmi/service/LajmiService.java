package com.localnews.lajmi.service;

import com.localnews.lajmi.client.KomentiClient;
import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.repository.LajmiRepository;
import com.localnews.lajmi.response.FullLajmiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LajmiService {

    private final KomentiClient komentiClient;
    private final LajmiRepository lajmiRepository;

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
                .kategoria(Kategoria.builder()
                        .name(lajmi.getKategoria().getName())
                        .build())
                .komentet(komentet)
                .build();
    }
}

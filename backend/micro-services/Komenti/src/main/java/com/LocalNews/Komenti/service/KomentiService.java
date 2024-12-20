package com.LocalNews.Komenti.service;

import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.repository.KomentiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KomentiService {

    private final KomentiRepository repository;

    public Komenti createKomenti(Komenti komenti) {
        if (komenti.getTeksti() == null || komenti.getTeksti().trim().isEmpty()) {
            throw new IllegalArgumentException("Teksti nuk munde te jete i zbrazet!");
        }
        return repository.save(komenti);
    }

    @Cacheable(value = "komenti", key="#id")
    public Komenti findKomentiById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Komenti nuk u gjete!"));
    }

    @CacheEvict(value = "komenti", key="#id")
    public void deleteKomenti(Integer id) {
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Komenti me ID " + id + " nuk ekziston!");
        }
        repository.deleteById(id);
    }

    @CacheEvict(value = "komenti", key="#id")
    public Komenti updateKomenti(Integer id, String teksti) {
        Komenti komenti = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Komenti nuk u gjete!")
        );

        komenti.setTeksti(teksti);

        return repository.save(komenti);

    }

    public List<Komenti> findAllKomentet() {
        return repository.findAll();
    }

    public void shtoReply(Integer komenti_id, Komenti reply) {
        Komenti komenti = repository.findById(komenti_id)
                .orElseThrow(() -> new IllegalArgumentException("Komenti nuk u gjet!"));

        reply.setParent(komenti);

        repository.save(reply);
    }

    public List<Komenti> getReplies(Integer komenti_id) {
        Komenti komenti  = repository.findById(komenti_id)
                .orElseThrow(() -> new IllegalArgumentException("Komenti nuk u gjet!"));

        return komenti.getReplies();
    }

    public List<Komenti> findAllKomentetByLajmiId(Integer lajmiId) {
        return repository.findAllByLajmiId(lajmiId);
    }

    public void deleteKomentetELajmit(Integer lajmiId) {
        List<Komenti> komentet = findAllKomentetByLajmiId(lajmiId);

        repository.deleteAll(komentet);
    }
}

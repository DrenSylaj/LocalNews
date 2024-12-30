package com.LocalNews.Komenti.service;

import com.LocalNews.Komenti.DTO.KomentiResponse;
import com.LocalNews.Komenti.DTO.UserDTO;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.repository.KomentiRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Komenti nuk u gjete!")
        );
    }

    @CacheEvict(value = "komenti", key="#id")
    public String deleteKomenti(Integer id, UserDTO user) {

        Komenti komenti = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Komenti me id" + id + " nuk u gjete!")
        );

        if (user.getId() != komenti.getCreatorId() && !user.getRole().toString().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Nuk keni autroizim per fshirjen e ktij komenti!");
        }
        repository.deleteById(id);

        return "Komenti u shlye me Sukses!";
    }

    @CacheEvict(value = "komenti", key="#id")
    public Komenti updateKomenti(Integer id, String teksti, UserDTO user) {
        Komenti komenti = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Komenti nuk u gjete!")
        );
        if (user.getId() != komenti.getCreatorId()) {
            throw new RuntimeException("Nuk keni autorizim per te bere update kete koment!");
        }
        komenti.setTeksti(teksti);
        return repository.save(komenti);
    }


    public List<Komenti> findAllKomentet() {
        return repository.findAll();
    }

    public void shtoReply(Integer komenti_id, String teksti, Integer userId) {
        Komenti komenti = repository.findById(komenti_id)
                .orElseThrow(() -> new IllegalArgumentException("Komenti nuk u gjet!"));

        Komenti reply = new Komenti();
        reply.setTeksti(teksti);
        reply.setParent(komenti);
        reply.setCreatorId(userId);
        reply.setLajmiId(komenti.getLajmiId());

        repository.save(reply);
    }

    public List<Komenti> getReplies(Integer komenti_id) {
        Komenti komenti  = repository.findById(komenti_id)
                .orElseThrow(() -> new IllegalArgumentException("Komenti nuk u gjet!"));

        return komenti.getReplies();
    }

    public List<Komenti> findAllKomentetByLajmiId(Integer lajmiId) {
        return repository.findByLajmiIdAndParentIsNull(lajmiId);
    }


    public void deleteKomentetELajmit(Integer lajmiId) {
        List<Komenti> komentet = findAllKomentetByLajmiId(lajmiId);

        repository.deleteAll(komentet);
    }

    // Infinite Scroll !!!
    public List<Komenti> getPaginatedComments(Integer lajmiId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Komenti> commentsPage = repository.findByLajmiIdAndParentIsNull(lajmiId, pageable);
        return commentsPage.getContent();
    }



    public List<Komenti> getReplies(Integer parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Komenti> commentsPage = repository.findByParentId(parentId, pageable);
        return commentsPage.getContent();
    }
}

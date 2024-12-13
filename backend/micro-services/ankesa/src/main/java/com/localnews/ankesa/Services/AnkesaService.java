package com.localnews.ankesa.Services;

import com.localnews.ankesa.Entity.Ankesa;
import com.localnews.ankesa.Repositories.AnkesaRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnkesaService {

    @Autowired
    private AnkesaRepository ankesaRepository;



    public Ankesa saveAnkesa(Ankesa ankesa) {

        return ankesaRepository.save(ankesa);
    }

    public List<Ankesa> getAllAnkesa() {
        return ankesaRepository.findAll();
    }

    public Ankesa getAnkesaById(Integer id) {
        return ankesaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Ankesa me Id-ne e dhene nuk u gjete!")
        );
    }

    public void deleteAnkesa(Integer id) {
        ankesaRepository.deleteById(id);
    }

    public void deleteAnkesatByUserId(Integer id) {
        ankesaRepository.deleteAllByUserId(id);
    }

    public List<Ankesa> getAnkesatByUserId(Integer userId) {
        return ankesaRepository.findAllByUserId(userId);
    }
}

package com.localnews.ankesa.Repositories;

import com.localnews.ankesa.Entity.Ankesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnkesaRepository extends JpaRepository<Ankesa, Integer> {
    List<Ankesa> findAllByUserId(Integer userId);
    void deleteAllByUserId(Integer userId);
}

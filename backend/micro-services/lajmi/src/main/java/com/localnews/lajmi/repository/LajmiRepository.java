package com.localnews.lajmi.repository;


import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Lajmi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LajmiRepository extends JpaRepository<Lajmi, Integer> {
    List<Lajmi> findByKategoria(Kategoria kategoria);
}

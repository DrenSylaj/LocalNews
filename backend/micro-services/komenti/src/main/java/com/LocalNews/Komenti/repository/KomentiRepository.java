package com.LocalNews.Komenti.repository;

import com.LocalNews.Komenti.entity.Komenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentiRepository extends JpaRepository<Komenti, Integer> {
    List<Komenti> findAllByLajmiId(Integer lajmiId);

}

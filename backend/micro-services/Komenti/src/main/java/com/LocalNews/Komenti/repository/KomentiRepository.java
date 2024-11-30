package com.LocalNews.Komenti.repository;

import com.LocalNews.Komenti.entity.Komenti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentiRepository extends JpaRepository<Komenti, Integer> {
}

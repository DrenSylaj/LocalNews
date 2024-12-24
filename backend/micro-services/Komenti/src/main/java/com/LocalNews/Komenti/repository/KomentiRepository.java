package com.LocalNews.Komenti.repository;

import com.LocalNews.Komenti.entity.Komenti;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KomentiRepository extends JpaRepository<Komenti, Integer> {

    List<Komenti> findAllByLajmiId(Integer lajmiId);

    List<Komenti> findByLajmiIdAndParentIsNull(Integer lajmiId);

    @Query("SELECT k FROM Komenti k WHERE k.lajmiId = :lajmiId AND k.parent IS NULL ORDER BY k.id ASC")
    Page<Komenti> findByLajmiIdAndParentIsNull(@Param("lajmiId") Integer lajmiId, Pageable pageable);

    @Query("SELECT k FROM Komenti k WHERE k.parent.id = :parentId ORDER BY k.id ASC")
    Page<Komenti> findByParentId(@Param("parentId") Integer parentId, Pageable pageable);



}

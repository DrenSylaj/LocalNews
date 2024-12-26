package com.localnews.user.repositories;

import com.localnews.user.entities.Autori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoriRepository extends JpaRepository<Autori, Integer> {
    Autori findByUserId(Integer userId);
}

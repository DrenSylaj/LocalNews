package com.localnews.lajmi.repository;


import com.localnews.lajmi.entity.Lajmi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LajmiRepository extends JpaRepository<Lajmi, Integer> {
}

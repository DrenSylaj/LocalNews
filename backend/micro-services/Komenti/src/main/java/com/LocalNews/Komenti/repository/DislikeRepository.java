package com.LocalNews.Komenti.repository;


import com.LocalNews.Komenti.entity.Dislike;
import com.LocalNews.Komenti.entity.Komenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DislikeRepository extends JpaRepository<Dislike, Integer> {
    List<Dislike> findByUserId(Integer userId);
    Optional<Dislike> findByUserIdAndComment(Integer userId, Komenti comment);


}

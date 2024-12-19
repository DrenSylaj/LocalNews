package com.LocalNews.Komenti.repository;

import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findByUserId(Integer userId);
    Optional<Like> findByUserIdAndComment(Integer userId, Komenti comment);

}

package com.LocalNews.Komenti.service;

import com.LocalNews.Komenti.DTO.UserDTO;
import com.LocalNews.Komenti.client.UserClient;
import com.LocalNews.Komenti.entity.Dislike;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.entity.Like;
import com.LocalNews.Komenti.repository.DislikeRepository;
import com.LocalNews.Komenti.repository.KomentiRepository;
import com.LocalNews.Komenti.repository.LikeRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final KomentiRepository komentiRepository;
    private final DislikeRepository dislikeRepository;

    private final UserClient userClient;

    @CacheEvict(value = "komenti", key="#id")
    public Like addLike(Integer userId, Integer id) {

        Komenti komenti = komentiRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Komenti nuk ekziston!")
        );


        if (isDisliked(userId, id)) {
            Dislike dislike = dislikeRepository.findByUserIdAndComment(userId, komenti).get();
            deleteDislike(dislike.getId());
        } else if (isLiked(userId, id)) {
            Like like = likeRepository.findByUserIdAndComment(userId, komenti).get();
            deleteLike(like.getId());
            return null;
        }

        Like like = new Like();
        like.setUserId(userId);
        like.setComment(komenti);

        return likeRepository.save(like);
    }

    @CacheEvict(value = "komenti", key="#id")
    public Dislike addDislike(Integer userId, Integer id) {

        if (isLiked(userId, id)) {
            Komenti komenti = komentiRepository.findById(id).get();
            Like like = likeRepository.findByUserIdAndComment(userId, komenti).get();

            deleteLike(like.getId());
        } else if (isDisliked(userId, id)) {
            Komenti komenti = komentiRepository.findById(id).get();
            Dislike dislike = dislikeRepository.findByUserIdAndComment(userId, komenti).get();

            deleteDislike(dislike.getId());
            return null;
        }

        UserDTO user = userClient.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Useri nuk ekziston!");
        }

        Komenti komenti = komentiRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Komenti nuk ekziston!")
        );

        Dislike dislike = new Dislike();
        dislike.setUserId(userId);
        dislike.setComment(komenti);

        return dislikeRepository.save(dislike);
    }

    public String deleteLike(Integer id) {
        likeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Like nuk u gjete!")
        );
        likeRepository.deleteById(id);

        return "Removed Like!";
    }

    public String deleteDislike(Integer id) {
        dislikeRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Dislike nuk u gjete!")
        );
        dislikeRepository.deleteById(id);

        return "Removed Dislike!";
    }

    public List<Like> getLikesByUserId(Integer userId) {
        return likeRepository.findByUserId(userId);
    }


    public boolean isLiked(Integer userId, Integer commentId) {
        Komenti komenti = komentiRepository.findById(commentId).orElseThrow(
                ()-> new RuntimeException("Komenti nuk u gjete!")
        );

        return likeRepository.findByUserIdAndComment(userId, komenti).isPresent();
    }

    public boolean isDisliked(Integer userId, Integer commentId) {
        Komenti komenti = komentiRepository.findById(commentId).orElseThrow(
                ()-> new RuntimeException("Komenti nuk u gjete!")
        );
        return dislikeRepository.findByUserIdAndComment(userId, komenti).isPresent();
    }


}

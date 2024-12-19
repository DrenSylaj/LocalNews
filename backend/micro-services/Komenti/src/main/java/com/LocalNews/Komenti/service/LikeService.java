package com.LocalNews.Komenti.service;

import com.LocalNews.Komenti.DTO.UserDTO;
import com.LocalNews.Komenti.client.UserClient;
import com.LocalNews.Komenti.entity.Dislike;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.entity.Like;
import com.LocalNews.Komenti.repository.DislikeRepository;
import com.LocalNews.Komenti.repository.KomentiRepository;
import com.LocalNews.Komenti.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final KomentiRepository komentiRepository;
    private final DislikeRepository dislikeRepository;

    private final UserClient userClient;

    public Like addLike(Integer userId, Integer commentId, boolean isLike) {

        if (isDisliked(userId, commentId)) {
            Komenti komenti = komentiRepository.findById(commentId).get();
            Dislike dislike = dislikeRepository.findByUserIdAndComment(userId, komenti).get();

            deleteDislike(dislike.getId());
        }


        UserDTO user = userClient.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Useri nuk ekziston!");
        }

        Komenti komenti = komentiRepository.findById(commentId).orElseThrow(
                () -> new RuntimeException("Komenti nuk ekziston!")
        );

        Like like = new Like();
        like.setUserId(userId);
        like.setComment(komenti);
        like.setIslike(isLike);

        return likeRepository.save(like);
    }

    public Dislike addDislike(Integer userId, Integer commentId, boolean isLike) {

        if (isLiked(userId, commentId)) {
            Komenti komenti = komentiRepository.findById(commentId).get();
            Like like = likeRepository.findByUserIdAndComment(userId, komenti).get();

            deleteLike(like.getId());
        }

        UserDTO user = userClient.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Useri nuk ekziston!");
        }

        Komenti komenti = komentiRepository.findById(commentId).orElseThrow(
                () -> new RuntimeException("Komenti nuk ekziston!")
        );

        Dislike dislike = new Dislike();
        dislike.setUserId(userId);
        dislike.setComment(komenti);
        dislike.setDisliked(isLike);

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

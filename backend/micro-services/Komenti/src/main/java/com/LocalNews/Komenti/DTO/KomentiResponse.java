package com.LocalNews.Komenti.DTO;

import com.LocalNews.Komenti.controller.KomentiController;
import com.LocalNews.Komenti.entity.Dislike;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.entity.Like;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KomentiResponse implements Serializable {

    private static final long serialVersionUID =1L;

    private int id;
    private String teksti;
    private int lajmiId;
    private Komenti parentId;
    private int creatorId;
    private int likes;
    private int dislikes;
}

package com.LocalNews.Komenti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Dislikes")
public class Dislike {
        @Id
        @GeneratedValue
        private Integer id;

        private Integer userId;

        @ManyToOne
        @JoinColumn(name = "comment_id")
        private Komenti comment;

        private boolean isDisliked;


}

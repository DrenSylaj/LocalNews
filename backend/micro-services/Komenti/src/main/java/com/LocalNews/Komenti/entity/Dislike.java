package com.LocalNews.Komenti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Dislikes")
public class Dislike implements Serializable {

        private static final long serialVersionUID =1L;


        @Id
        @GeneratedValue
        private Integer id;

        private Integer userId;

        @ManyToOne
        @JoinColumn(name = "comment_id", nullable = false)
        @JsonIgnore
        private Komenti comment;

        private boolean isDisliked;


}

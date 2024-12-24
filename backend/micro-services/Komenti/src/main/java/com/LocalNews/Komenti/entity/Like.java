package com.LocalNews.Komenti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.Comment;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Komenti comment;

    private boolean isLike;

    public void setIslike(boolean state) {
        isLike = state;
    }
}

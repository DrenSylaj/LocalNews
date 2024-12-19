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
    @GeneratedValue
    private Integer id;

    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Komenti comment;

    private boolean isLike;

    public void setIslike(boolean state) {
        isLike = state;
    }
}

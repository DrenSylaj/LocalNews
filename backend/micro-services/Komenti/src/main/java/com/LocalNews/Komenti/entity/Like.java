package com.LocalNews.Komenti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.xml.stream.events.Comment;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Likes")
public class Like implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    @JsonIgnore
    private Komenti comment;

    private boolean isLike;

    public void setIslike(boolean state) {
        isLike = state;
    }
}

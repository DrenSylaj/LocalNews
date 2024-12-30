package com.LocalNews.Komenti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class Komenti implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String teksti;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Dislike> dislikes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Komenti parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Komenti> replies = new ArrayList<>();

    private Integer lajmiId;

    @Column(name = "creator_id")
    private Integer creatorId;


    @JsonProperty("likes")
    public int getLikeCount() {
        return likes != null ? likes.size() : 0;
    }

    @JsonProperty("dislikes")
    public int getDislikeCount() {
        return dislikes != null ? dislikes.size() : 0;
    }
}


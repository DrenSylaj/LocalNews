package com.LocalNews.Komenti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Komenti implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String teksti;

    private Integer likes = 0;
    private Integer dislikes = 0;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Komenti parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Komenti> replies = new ArrayList<>();

    private Integer lajmiId;
}


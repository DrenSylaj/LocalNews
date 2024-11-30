package com.LocalNews.Komenti.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Komenti {

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
    private List<Komenti> replies = new ArrayList<>();
}


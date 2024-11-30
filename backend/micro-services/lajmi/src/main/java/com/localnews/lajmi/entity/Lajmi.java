package com.localnews.lajmi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lajmi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String teksti;

    @CreationTimestamp
    private Timestamp kohaPublikimit;

    @ManyToOne(optional = false)
    private Kategoria kategoria;

    private Integer autoriId;

}

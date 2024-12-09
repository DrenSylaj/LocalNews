package com.localnews.lajmi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagLajmi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Lajmi lajmi;


    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Tag tag;
}

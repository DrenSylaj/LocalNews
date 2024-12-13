package com.localnews.ankesa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ankesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String tema;
    public String teksti;
    public String lloji;
    public String statusi;

    public Integer userId;
}

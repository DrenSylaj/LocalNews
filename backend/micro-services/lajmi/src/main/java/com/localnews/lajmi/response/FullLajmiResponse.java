package com.localnews.lajmi.response;

import com.localnews.lajmi.entity.Kategoria;
import lombok.*;


import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullLajmiResponse {
    private String teksti;

    private Timestamp kohaPublikimit;

    private Kategoria kategoria;

    private Integer autoriId;

    List<Komenti> komentet;
}

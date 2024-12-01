package com.localnews.lajmi.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Komenti {
    private String teksti;

    private Integer likes = 0;
    private Integer dislikes = 0;

    private Komenti parent;

    private List<Komenti> replies = new ArrayList<>();
}

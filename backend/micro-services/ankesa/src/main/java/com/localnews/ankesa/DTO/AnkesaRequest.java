package com.localnews.ankesa.DTO;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnkesaRequest {

    public String tema;
    public String teksti;
    public String lloji;

    public String getTema() {
        return tema;
    }

    public String getTeksti() {
        return teksti;
    }

    public String getLloji() {
        return lloji;
    }

}
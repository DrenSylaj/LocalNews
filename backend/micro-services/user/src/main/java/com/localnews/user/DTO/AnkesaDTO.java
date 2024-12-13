package com.localnews.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnkesaDTO {
    private Integer id;
    private String tema;
    private String teksti;
    private String lloji;
    private String statusi;
    private Integer userId;
}

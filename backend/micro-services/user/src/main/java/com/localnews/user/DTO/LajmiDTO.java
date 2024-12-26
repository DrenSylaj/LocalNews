package com.localnews.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LajmiDTO {
    private Integer id;
    private String teksti;
    private Timestamp kohaPublikimit;
    private Integer kategoriaId;
    private Integer autoriId;

}

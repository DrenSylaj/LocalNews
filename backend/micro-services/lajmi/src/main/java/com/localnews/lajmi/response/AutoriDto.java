package com.localnews.lajmi.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class AutoriDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dataLindjes;
    private String gjinia;
    private String vendbanimi;
    private String bibliografia;
}

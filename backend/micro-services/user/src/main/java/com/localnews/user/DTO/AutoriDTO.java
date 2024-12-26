package com.localnews.user.DTO;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AutoriDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dataLindjes;
    private String gjinia;
    private String vendbanimi;
    private String bibliografia;
}


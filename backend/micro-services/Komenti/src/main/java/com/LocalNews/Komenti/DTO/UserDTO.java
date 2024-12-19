package com.LocalNews.Komenti.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dataLindjes;
    private String gjinia;
    private String vendbanimi;
    private String email;
    private String password;
}

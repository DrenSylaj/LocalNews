package com.localnews.ankesa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public int id;
    private String firstName;
    private String lastName;
    private LocalDate dataLindjes;
    private String gjinia;
    private String vendbanimi;
    private String email;
    private String password;
    public Role role;

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }
}

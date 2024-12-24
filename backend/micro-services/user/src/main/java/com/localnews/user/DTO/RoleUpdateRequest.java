package com.localnews.user.DTO;

import com.localnews.user.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateRequest {
    private Role role;
}

package com.localnews.ankesa.Client;

import com.localnews.ankesa.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserClient", url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/jwt")
    public UserDTO getUserByJwt(@RequestHeader("Authorization") String jwt);

}

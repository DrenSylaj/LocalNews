package com.LocalNews.Komenti.client;

import com.LocalNews.Komenti.DTO.UserDTO;
import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserClient", url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/{id}")
    UserDTO findUserById(@PathVariable("id") Integer id);
}

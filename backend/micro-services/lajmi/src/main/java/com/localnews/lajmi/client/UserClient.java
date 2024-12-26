package com.localnews.lajmi.client;

import com.localnews.lajmi.response.AutoriDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserClient", url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping
    public AutoriDto autoriByJwt(@RequestHeader("Authorization") String jwt);
}

package com.localnews.user.Client;

import com.localnews.user.DTO.AnkesaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "AnkesaClient", url = "${application.config.ankesat-url}")
public interface AnkesaClient {

    @GetMapping("/user/{userId}")
    List<AnkesaDTO> getAnkesatByUserId(@PathVariable Integer userId);

    @DeleteMapping("/delete/{userId}")
    void deleteAnkesatByUserId(@PathVariable Integer userId);
}


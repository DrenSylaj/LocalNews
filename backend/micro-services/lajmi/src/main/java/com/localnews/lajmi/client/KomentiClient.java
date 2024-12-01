package com.localnews.lajmi.client;

import com.localnews.lajmi.response.Komenti;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "komentiClient", url= "${application.config.komentet-url}")
public interface KomentiClient {

    @GetMapping("/lajmi/{lajmi-id}")
    List<Komenti> findAllKomentetByLajmi(
            @PathVariable("lajmi-id") Integer lajmiId);
}

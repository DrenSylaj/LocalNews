package com.localnews.lajmi.controller;

import com.localnews.lajmi.client.UserClient;
import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.response.AutoriDto;
import com.localnews.lajmi.response.FullLajmiResponse;
import com.localnews.lajmi.service.LajmiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/v1/lajmet")
@RequiredArgsConstructor
public class LajmiController {

    private final Logger logger = LoggerFactory.getLogger(LajmiController.class);
    private final LajmiService lajmiService;

    @GetMapping
    public ResponseEntity<List<Lajmi>> findAllLajmet(){
        return ResponseEntity.ok(lajmiService.findAllLajmet());
    }

    @GetMapping("/{id}")
    public Lajmi findLajmiById(
            @PathVariable int id
    ){
        return lajmiService.findById(id);
    }

    @GetMapping("/with-comments/{lajmi-id}")
    public ResponseEntity<FullLajmiResponse> findLajmetWithComments(
            @PathVariable("lajmi-id") Integer lajmiId
    ){
        logger.info("Comments of lajmi with id: "+lajmiId+" have been retrieved");
        return ResponseEntity.ok(lajmiService.findLajmetWithComments(lajmiId));
    }

    @GetMapping("/autori/{autoriId}")
    public List<Lajmi> getLajmetByAutoriId(@PathVariable("autori-id") Integer autoriId){
        return lajmiService.findByAutoriId(autoriId);

    }


}

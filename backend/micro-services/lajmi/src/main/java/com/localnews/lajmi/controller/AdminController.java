package com.localnews.lajmi.controller;

import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Tag;
import com.localnews.lajmi.service.KategoriaService;
import com.localnews.lajmi.service.LajmiService;
import com.localnews.lajmi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adminLajmi")
@RequiredArgsConstructor
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final LajmiService lajmiService;
    private final KategoriaService kategoriaService;
    private final TagService tagService;

    @DeleteMapping("/lajmi/{id}")
    public String deleteLajmi(@PathVariable Integer id) {
        logger.info("Lajmi with id: "+id+" has been deleted");
        return lajmiService.deleteLajmi(id);

    }

    @PostMapping("/kategoria")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveKategoria(@RequestBody Kategoria kategoria){
        kategoriaService.saveKategoria(kategoria);
    }

    @DeleteMapping("/kategoria/{id}")
    public void deleteKategoria(@PathVariable Integer id){
        kategoriaService.deleteKategoria(id);
        logger.info("Kategoria with id: "+id+" has been deleted");
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTag(@RequestBody Tag tag){
        tagService.saveTag(tag);
    }

    @DeleteMapping("/tag/{id}")
    public void deleteTagById(@PathVariable Integer id){
        tagService.deleteTagById(id);
    }

}

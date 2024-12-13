package com.localnews.ankesa.Controllers;

import com.localnews.ankesa.Entity.Ankesa;
import com.localnews.ankesa.Services.AnkesaService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ankesa")
public class AnkesaController {

    @Autowired
    private AnkesaService ankesaService;

    @PostMapping
    public ResponseEntity<Ankesa> createOrUpdateAnkesa(@RequestBody Ankesa ankesa) {
        Ankesa savedAnkesa = ankesaService.saveAnkesa(ankesa);
        return ResponseEntity.ok(savedAnkesa);
    }

    @GetMapping
    public ResponseEntity<List<Ankesa>> getAllAnkesa() {
        List<Ankesa> ankesas = ankesaService.getAllAnkesa();
        return ResponseEntity.ok(ankesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ankesa> getAnkesaById(@PathVariable Integer id) {
        Ankesa ankesa = ankesaService.getAnkesaById(id);
        return ResponseEntity.ok(ankesa);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ankesa>> getAnkesatByUserId(@PathVariable Integer userId) {
        List<Ankesa> ankesat = ankesaService.getAnkesatByUserId(userId);
        return ResponseEntity.ok(ankesat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnkesaById(@PathVariable Integer id) {
        ankesaService.deleteAnkesa(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteAnkesatByUserId(@PathVariable Integer userId) {
        ankesaService.deleteAnkesatByUserId(userId);
    }


}

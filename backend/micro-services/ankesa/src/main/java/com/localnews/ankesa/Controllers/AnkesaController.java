package com.localnews.ankesa.Controllers;

import com.localnews.ankesa.DTO.AnkesaRequest;
import com.localnews.ankesa.Entity.Ankesa;
import com.localnews.ankesa.Services.AnkesaService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ankesa")
public class AnkesaController {

    @Autowired
    private AnkesaService ankesaService;

    @PostMapping
    public ResponseEntity<Ankesa> createAnkesa(
            @RequestBody Ankesa ankesa,
            @RequestHeader("Authorization") String token) {
        Ankesa savedAnkesa = ankesaService.saveAnkesa(ankesa, token);
        return ResponseEntity.ok(savedAnkesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ankesa> updateAnkesa(
            @PathVariable Integer id,
            @RequestBody AnkesaRequest ankesaRequest,
            @RequestHeader("Authorization") String token) throws NoPermissionException {
        Ankesa savedAnkesa = ankesaService.updateAnkesa(id, ankesaRequest,token);
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
    public ResponseEntity<Void> deleteAnkesaById(@PathVariable Integer id,
                                                 @RequestHeader("Authorization") String token) throws NoPermissionException {
        ankesaService.deleteAnkesa(id, token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteAnkesatByUserId(@PathVariable Integer userId) {
        ankesaService.deleteAnkesatByUserId(userId);
    }


}

package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Adestrador;
import org.example.service.AdestradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AdestradorController.MAPPING)
public class AdestradorController {

    public static final String MAPPING = "/adestrador";

    @Autowired
    private AdestradorService adestradorService;

    @Operation(summary = "Crear un nuevo adestrador")
    @PostMapping("/adestrador")
    public Adestrador crearAdestrador(@RequestBody Adestrador adestrador) {
        return adestradorService.saveAdestrador(adestrador);
    }

    @Operation(summary = "Obtener todos los adestradores")
    @GetMapping("/adestradores")
    public List<Adestrador> obtenerAdestradores() {
        return adestradorService.getAllAdestradores();
    }

    @Operation(summary = "Obtener adestrador por id")
    @GetMapping("/getadestrador/{id}")
    public ResponseEntity<Adestrador> obtenerAdestradorId(@PathVariable Long id) {
        return adestradorService.getAdestradorbyId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un adestrador")
    @DeleteMapping("/deleteadestrador/{id}")
    public ResponseEntity<Void> eliminarAdestradorId(@PathVariable Long id) {
        if (adestradorService.deleteAdestradorId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
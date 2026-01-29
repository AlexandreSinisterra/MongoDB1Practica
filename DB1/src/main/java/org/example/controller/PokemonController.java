package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.model.Pokemon;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PokemonController.MAPPING)
public class PokemonController {

    public static final String MAPPING = "/pokemon";

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Crear un nuevo pokemon")
    @PostMapping("/pokemon")
    public ResponseEntity<?> crearPokemon(@RequestBody Pokemon pokemon) {
        try {
            return ResponseEntity.ok(pokemonService.savePokemon(pokemon));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener todos los pokemons")
    @GetMapping("/pokemons")
    public List<Pokemon> obtenerPokemons() {
        return pokemonService.getAllPokemons();
    }

    @Operation(summary = "Obtener pokemon por ID")
    @GetMapping("/getpokemon/{id}")
    public ResponseEntity<Pokemon> obtenerPokemonId(@PathVariable String id) {
        return pokemonService.getPokemonId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un pokemon")
    @DeleteMapping("/deletepokemon/{id}")
    public ResponseEntity<Void> eliminarPokemonId(@PathVariable String id) {
        if (pokemonService.deletePokemonId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
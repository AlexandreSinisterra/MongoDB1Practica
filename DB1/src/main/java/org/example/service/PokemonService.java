package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.repository.PokemonRepository;
import org.example.repository.AdestradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final AdestradorRepository adestradorRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, AdestradorRepository adestradorRepository) {
        this.pokemonRepository = pokemonRepository;
        this.adestradorRepository = adestradorRepository;
    }

    @Transactional
    public Pokemon savePokemon(Pokemon pokemon) {
        String idAdestrador = pokemon.getIdAdestrador();

        if (idAdestrador == null || idAdestrador.isEmpty()) {
            throw new IllegalArgumentException("El Pokemon debe tener un adestrador");
        }

        Adestrador adestrador = adestradorRepository.findById(idAdestrador)
                .orElseThrow(() -> new EntityNotFoundException("Adestrador " + idAdestrador + " no encontrado."));

        pokemon.setAdestrador(adestrador);

        return pokemonRepository.save(pokemon);
    }

    @Transactional
    public Pokemon updatePokemon(String id, Pokemon nuevoPokemon) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon no encontrado " + id));

        pokemon.setNome(nuevoPokemon.getNome());
        pokemon.setTipo(nuevoPokemon.getTipo());
        pokemon.setNivel(nuevoPokemon.getNivel());
        pokemon.setHabilidades(nuevoPokemon.getHabilidades());

        String nuevoIdAdestrador = nuevoPokemon.getIdAdestrador();

        if(nuevoIdAdestrador != null && !nuevoIdAdestrador.isEmpty()) {
            Adestrador nuevoAdestrador = adestradorRepository.findById(nuevoIdAdestrador)
                    .orElseThrow(() -> new EntityNotFoundException("Nuevo Adestrador no encontrado"));

            pokemon.setAdestrador(nuevoAdestrador);
            pokemon.setIdAdestrador(nuevoIdAdestrador);
        }
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> getPokemonId(String id) {
        return pokemonRepository.findById(id);
    }

    public boolean deletePokemonId(String id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.Adestrador;
import org.example.repository.AdestradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdestradorService {

    private final AdestradorRepository adestradorRepository;

    @Autowired
    public AdestradorService(AdestradorRepository adestradorRepository){
        this.adestradorRepository = adestradorRepository;
    }
    @Transactional
    public Adestrador saveAdestrador(Adestrador adestrador){
        return adestradorRepository.save(adestrador);
    }
    @Transactional
    public Adestrador updateAdestrador(Long id, Adestrador adestrador){
        Adestrador NAdestrador = adestradorRepository.findById(String.valueOf(id)).orElseThrow(()->new EntityNotFoundException("adestradoe no entfontrasdo"));
        NAdestrador.setNome(adestrador.getNome());
        NAdestrador.setCidade(adestrador.getCidade());
        NAdestrador.setIdade(adestrador.getIdade());
        return adestradorRepository.save(NAdestrador);
    }
    public List<Adestrador> getAllAdestradores() {
        return adestradorRepository.findAll();
    }

    public Optional<Adestrador> getAdestradorbyId(Long id) {
        return adestradorRepository.findById(String.valueOf(id));
    }

    public boolean deleteAdestradorId(Long id) {
        if (adestradorRepository.existsById(String.valueOf(id))) {
            adestradorRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}

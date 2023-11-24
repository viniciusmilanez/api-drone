package com.dronedelivery.apidrone.services;

import com.dronedelivery.apidrone.entreprise.NotFoundException;
import com.dronedelivery.apidrone.model.Entrega;
import com.dronedelivery.apidrone.repositories.EntregaRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public Entrega salvar(Entrega entity) {

    

        return repo.save(entity);
    }

    public List<Entrega> buscaTodos(String filter) {
        return repo.findAll(filter, Entrega.class);
    }

    public Page<Entrega> buscaTodos(String filter, Pageable pageable) {
        return repo.findAll(filter, Entrega.class, pageable);
    }

    public Entrega buscaPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Entrega alterar(Long id, Entrega entity) {
        Optional<Entrega> byId = repo.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("Entrega não encontrado");
        }

        Entrega existingEntrega = byId.get();
        modelMapper.map(entity, existingEntrega);

        return repo.save(existingEntrega);
    }

    public void remover(Long id) {
        repo.deleteById(id);
    }
}

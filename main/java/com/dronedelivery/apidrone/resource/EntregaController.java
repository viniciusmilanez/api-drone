package com.dronedelivery.apidrone.resource;

import com.dronedelivery.apidrone.entreprise.NotFoundException;
import com.dronedelivery.apidrone.model.Entrega;
import com.dronedelivery.apidrone.services.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/Entregas")
public class EntregaController extends AbstractController {

    @Autowired
    private EntregaService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated Entrega entity) {
        Entrega save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/Entregas/" + entity.getIdEntrega())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Entrega> entregas = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<EntregaDTO> entregaDTOS = EntregaDTO.fromEntity(entregas);
        return ResponseEntity.ok(entregaDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Entrega Entrega = service.buscaPorId(id);
        return ResponseEntity.ok(Entrega);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Entrega entity) {
        try {
            Entrega alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}
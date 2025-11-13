package com.example.ProjetoIntegradorweb.api.controller;

import com.example.ProjetoIntegradorweb.domian.model.cliente;
import com.example.ProjetoIntegradorweb.application.ClienteMapper;
import com.example.ProjetoIntegradorweb.domian.repository.ClienteRepository;
import com.example.ProjetoIntegradorweb.api.dto.ClienteRequestDTO;
import com.example.ProjetoIntegradorweb.api.dto.ClienteResponseDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping
public class ClienteController{
    @Autowired
    private final ClienteRepository repository;

    public ClienteController (ClienteRepository repository) {
        this.repository = repository;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@Valid @RequestBody ClienteRequestDTO dto){
        cliente entity = ClienteMapper.toEntity(dto);
        cliente saved = repository.save(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(ClienteMapper.toResponse(saved));
    }

    //READ - paginando, com busca opcional por nome (?q=)
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> list(
            Pageable pageable,
            @RequestParam(name = "q", required = false) String q
    ) {
        Page<cliente> page =(q == null || q.isBlank())
                ? repository.findAll(pageable)
                :repository.findByNomeContainingCase(q, pageable);

        Page<ClienteResponseDTO> body = page.map(ClienteMapper::toResponse);
        return ResponseEntity.ok(body);
    }

    //READ by id
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(
            @PathVariable Integer id,
            @Valid@RequestBody ClienteRequestDTO dto
    ) {
        cliente cliente = repository.findById(Long.valueOf(id))
                .orElseThrow(()->new ResponseStatusException(NOT_FOUND, "cliente não encontrado"));
        ClienteMapper.updateEntity(dto, cliente);
        cliente update = repository.save(cliente);

        return ResponseEntity.ok(ClienteMapper.toResponse(update));
    }
    // DELETE - por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            repository.deleteById(Long.valueOf(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(NOT_FOUND, "cliente não encontrado");
        }
        return ResponseEntity.noContent().build();
    }
}





































package com.example.ProjetoIntegradorweb.api.controller;

import com.example.ProjetoIntegradorweb.api.dto.garagemDTO;
import com.example.ProjetoIntegradorweb.api.dto.garagemRequestDTO;
import com.example.ProjetoIntegradorweb.domian.service.garagemService;

import jakarta.validation.Valid;

import java.net.URI;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(path = "/api/garagens")
public class garagemController {
    @Autowired
    private final garagemService service;

    public garagemController(garagemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<garagemDTO> create(@Valid @RequestBody garagemRequestDTO req,
                                            UriComponentsBuilder uriBuilder) {
        garagemDTO dto = service.criar(req);
        URI location = uriBuilder
                .path("/api/garagens/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<garagemDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    public ResponseEntity<List<garagemDTO>> listar(@RequestParam(required = false) Integer clienteId) {
        List<garagemDTO> lista = (clienteId != null)
                ? service.listarPorCliente(clienteId)
                : service.listar();
        return ResponseEntity.ok(lista);
    }

    // PUT = substituição completa (campos obrigatórios devem vir preenchidos)
    @PutMapping("/{id}")
    public ResponseEntity<garagemDTO> atualizar(@PathVariable Integer id,
                                                @Valid @RequestBody garagemRequestDTO req) {
        return ResponseEntity.ok(service.atualizar(id, req));
    }

    // PATCH = atualização parcial (campos null são ignorados no service/mapper)
    @PatchMapping("/{id}")
    public ResponseEntity<garagemDTO> atualizarParcial(@PathVariable Integer id,
                                                       @RequestBody garagemRequestDTO req) {
        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

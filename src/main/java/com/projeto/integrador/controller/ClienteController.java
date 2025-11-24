package com.projeto.integrador.controller;

import com.projeto.integrador.dto.ClienteDTO;
import com.projeto.integrador.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        try {
            ClienteDTO cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDTO>> findByNome(@RequestParam String nome) {
        List<ClienteDTO> clientes = clienteService.findByNome(nome);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdCliente = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO updatedCliente = clienteService.update(id, clienteDTO);
            return ResponseEntity.ok(updatedCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            clienteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.projeto.integrador.controller;

import com.projeto.integrador.dto.GaragemDTO;
import com.projeto.integrador.service.GaragemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garagens")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GaragemController {

    private final GaragemService garagemService;

    @GetMapping
    public ResponseEntity<List<GaragemDTO>> findAll() {
        List<GaragemDTO> garagens = garagemService.findAll();
        return ResponseEntity.ok(garagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GaragemDTO> findById(@PathVariable Integer id) {
        try {
            GaragemDTO garagem = garagemService.findById(id);
            return ResponseEntity.ok(garagem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<GaragemDTO>> findByClienteId(@PathVariable Integer clienteId) {
        List<GaragemDTO> garagens = garagemService.findByClienteId(clienteId);
        return ResponseEntity.ok(garagens);
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<GaragemDTO>> findByAno(@PathVariable Integer ano) {
        List<GaragemDTO> garagens = garagemService.findByAno(ano);
        return ResponseEntity.ok(garagens);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<GaragemDTO>> findByAnoAndMes(
            @RequestParam Integer ano,
            @RequestParam Integer mes) {
        List<GaragemDTO> garagens = garagemService.findByAnoAndMes(ano, mes);
        return ResponseEntity.ok(garagens);
    }

    @PostMapping
    public ResponseEntity<GaragemDTO> create(@Valid @RequestBody GaragemDTO garagemDTO) {
        try {
            GaragemDTO createdGaragem = garagemService.create(garagemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGaragem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GaragemDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody GaragemDTO garagemDTO) {
        try {
            GaragemDTO updatedGaragem = garagemService.update(id, garagemDTO);
            return ResponseEntity.ok(updatedGaragem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            garagemService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
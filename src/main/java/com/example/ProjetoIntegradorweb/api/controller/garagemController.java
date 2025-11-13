package com.example.ProjetoIntegradorweb.api.controller;

import com.example.ProjetoIntegradorweb.api.dto.garagemDTO;
import com.example.ProjetoIntegradorweb.api.dto.garagemRequestDTO;
import com.example.ProjetoIntegradorweb.domian.service.garagemService;

import jakarta.validation.Valid;
import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/garagens")
public class garagemController {
    private final garagemService garagemService;

    public garagemController(garagemService garagemService){
        this.garagemService = garagemService;
    }

    //POST /api/garagens
    @PostMapping
    public
}

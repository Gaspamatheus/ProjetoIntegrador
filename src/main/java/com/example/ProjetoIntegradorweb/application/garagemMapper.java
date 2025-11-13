package com.example.ProjetoIntegradorweb.application;

import com.example.ProjetoIntegradorweb.api.dto.garagemRequestDTO;
import com.example.ProjetoIntegradorweb.domian.model.cliente;
import com.example.ProjetoIntegradorweb.domian.model.garagem;


public final class garagemMapper {
    private  garagemMapper() {}

    public static garagem toEntity(garagemRequestDTO dto, cliente cliente) {
        garagem g = new garagem();
        g.setAno(dto.ano());
        g.setMesPago(dto.mesPago());
        g.setGaragemFechada(dto.garagemFechada());
        g.setValorGaragem(dto.valorGaragem());
        g.setCliente(cliente);
        return g;
    }

    public static void updateEntity(garagemRequestDTO dto, garagem g, cliente cliente) {
        if (dto.ano() != null) g.setAno(dto.ano());
        if (dto.mesPago() != null) g.setMesPago(dto.mesPago());
        if (dto.garagemFechada() != null) g.setGaragemFechada(dto.garagemFechada());
        if (dto.valorGaragem() != null) g.setValorGaragem(dto.valorGaragem());
        if (cliente != null) g.setCliente(cliente);
    }
}


package com.example.ProjetoIntegradorweb.api.dto;

import com.example.ProjetoIntegradorweb.domian.model.garagem;

import java.math.BigDecimal;

public record garagemDTO(
        Integer id,
        Integer ano,
        Integer mesPago,
        boolean garagemFechada,
        BigDecimal valorGaragem,
        Integer clienteId

) {
    public static garagemDTO fromEntity(garagem g){
        return new garagemDTO(
                g.getId(),
                g.getAno(),
                g.getMesPago(),
                g.getGaragemFechada(),
                g.getValorGaragem(),
                g.getCliente() != null ? g.getCliente().getId() : null

        );
    }
}

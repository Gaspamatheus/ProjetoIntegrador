package com.example.ProjetoIntegradorweb.api.dto;


import com.example.ProjetoIntegradorweb.domian.model.cliente;

import java.math.BigDecimal;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        BigDecimal valorPago
) {
    public static ClienteResponseDTO fromEntity(cliente c) {
        return new ClienteResponseDTO(c.getId(), c.getNome(), c.getValor_pago());
    }
}
package com.example.ProjetoIntegradorweb.application;


import com.example.ProjetoIntegradorweb.api.dto.ClienteRequestDTO;
import com.example.ProjetoIntegradorweb.api.dto.ClienteResponseDTO;
import com.example.ProjetoIntegradorweb.domian.model.cliente;

public final class ClienteMapper {

    private ClienteMapper() {}

    public static cliente toEntity(ClienteRequestDTO dto) {
        if (dto == null) return null;
        return new cliente(dto.nome(), dto.valorPago());
    }

    public static void updateEntity(ClienteRequestDTO dto, cliente entity) {
        if (dto == null || entity == null) return;
        entity.setNome(dto.nome());
        entity.setValor_pago(dto.valorPago());
    }

    public static ClienteResponseDTO toResponse(cliente entity) {
        if (entity == null) return null;
        return new ClienteResponseDTO(entity.getId(), entity.getNome(), entity.getValor_pago());
    }
}
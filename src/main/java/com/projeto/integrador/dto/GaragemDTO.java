package com.projeto.integrador.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GaragemDTO {

    private Integer id;

    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1900, message = "Ano deve ser maior ou igual a 1900")
    @Max(value = 2155, message = "Ano deve ser menor ou igual a 2155")
    private Integer ano;

    @NotNull(message = "Mês pago é obrigatório")
    @Min(value = 1, message = "Mês deve estar entre 1 e 12")
    @Max(value = 12, message = "Mês deve estar entre 1 e 12")
    private Integer mesPago;

    private Boolean garagemFechada;

    @NotNull(message = "Valor da garagem é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor da garagem deve ser maior que zero")
    private BigDecimal valorGaragem;

    @NotNull(message = "ID do cliente é obrigatório")
    private Integer clienteId;
}
package com.projeto.integrador.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Valor pago é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor pago deve ser maior que zero")
    private BigDecimal valorPago;
}
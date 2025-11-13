package com.example.ProjetoIntegradorweb.api.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

import org.antlr.v4.runtime.misc.NotNull;

public record ClienteRequestDTO(
        @NotBlank @Size(max=100) String nome,
        @NotNull @Digits (integer = 8, fraction = 2) BigDecimal valorPago
) {}

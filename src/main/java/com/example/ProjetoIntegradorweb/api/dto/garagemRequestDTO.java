package com.example.ProjetoIntegradorweb.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record garagemRequestDTO (
        @NotNull @Min(1901) @Max(2155) Integer ano,
        @NotNull @Min(1) @Max(12) Integer mesPago,
        boolean garagemFechada,
        @NotNull @Digits(integer = 8, fraction = 2) BigDecimal valorGaragem,
        Integer clienteId
) {}

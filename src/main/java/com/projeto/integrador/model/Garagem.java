package com.projeto.integrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "garagem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer ano;

    @Column(name = "mes_pago", nullable = false)
    private Integer mesPago;

    @Column(name = "garagem_fechada")
    private Boolean garagemFechada;

    @Column(name = "valor_garagem", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorGaragem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

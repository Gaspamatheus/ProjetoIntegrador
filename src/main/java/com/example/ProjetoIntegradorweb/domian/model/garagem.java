package com.example.ProjetoIntegradorweb.domian.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "garagem")
//@org.hibernate.annotations.check(constraints = "mes_pago BETWEEN 1 AND 12") //OPCIONAL
public class garagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //MySQL YEAR -> Integer (compativel)
    @NotNull
    @Min(1)@Max(12)
    @Column(name = "ano", nullable = false)
    private Integer ano;

    //tinyint 1...12
    @NotNull
    @Min(1) @Max(12)
    @Column(name = "mes_pago", nullable = false)
    private Integer mesPago;

    @Column(name = "garagem_fechada")
    private boolean garagemFechada;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name ="valor_garagem")
    private BigDecimal valorGaragem;

    //FK -> cliente(id)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_cliente"))
    private cliente cliente;

    public garagem(){}

    public garagem (Integer ano, Integer mesPago, Boolean garagemFechada, BigDecimal valorGaragem,
    cliente cliente) {
        this.ano = ano;
        this.mesPago = mesPago;
        this.garagemFechada = garagemFechada;
        this.valorGaragem = valorGaragem;
        this.cliente = cliente;
    }

    //Getter/setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMesPago() {
        return mesPago;
    }

    public void setMesPago(Integer mesPago) {
        this.mesPago = mesPago;
    }

    public boolean getGaragemFechada() {
        return garagemFechada;
    }

    public void setGaragemFechada(boolean garagemFechada) {
        this.garagemFechada = garagemFechada;
    }

    public BigDecimal getValorGaragem() {
        return valorGaragem;
    }

    public void setValorGaragem(BigDecimal valorGaragem) {
        this.valorGaragem = valorGaragem;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    // Igualdade baseada no ID (compatível com proxies)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof garagem other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Garagem{id=%d, ano=%d, mesPago=%d, fechada=%s, valor=%s, clienteId=%s}"
                .formatted(id, ano, mesPago, garagemFechada, valorGaragem,
                        cliente != null ? cliente.getId() : null);
    }
}

package com.example.ProjetoIntegradorweb.domian.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Entity
public class cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "valor_pago", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_pago;

    //construtor default exigido pela JPA
    protected cliente(){}

    public cliente(String nome, BigDecimal valor_pago){
        this.nome = nome;
        this.valor_pago = valor_pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(BigDecimal valor_pago) {
        this.valor_pago = valor_pago;
    }

    //igualdade por ID (jeito certo para hibernate proxies
    @Override
    public boolean equals (Object o) {
        if(this == o)return true;
        if(!(o instanceof cliente other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "cliente{id=%d, nome='%s', valorPago=%s}".formatted(id, nome, valor_pago);
    }
}

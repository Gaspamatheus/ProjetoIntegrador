package com.example.ProjetoIntegradorweb.domian.repository;

import com.example.ProjetoIntegradorweb.domian.model.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<cliente,Integer> {
    boolean existsByNomeignoreCase(String nome);
}

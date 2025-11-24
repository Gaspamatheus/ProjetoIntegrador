package com.projeto.integrador.repository;

import com.projeto.integrador.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);
}
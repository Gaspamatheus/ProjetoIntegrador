package com.projeto.integrador.repository;

import com.projeto.integrador.model.Garagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaragemRepository extends JpaRepository<Garagem, Integer> {

    List<Garagem> findByClienteId(Integer clienteId);

    List<Garagem> findByAno(Integer ano);

    List<Garagem> findByAnoAndMesPago(Integer ano, Integer mesPago);

    List<Garagem> findByGaragemFechada(Boolean fechada);
}
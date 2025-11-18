package com.example.ProjetoIntegradorweb.domian.repository;

import com.example.ProjetoIntegradorweb.domian.model.garagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface garagemRepository extends JpaRepository<garagem, Integer> {
    List<garagem> findByCienteId(Integer ClienteId);
    boolean existsByClienteIdAndAnoAndMesPago(Integer clienteId, Integer ano, Integer mesPago);


}

package com.example.ProjetoIntegradorweb.domian.repository;

import com.example.ProjetoIntegradorweb.domian.model.garagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface garagemRepository {
    //Lista garagens de um cliente
    List<garagem> findByCienteId(Integer ClienteId);

    //Útil para ecitar duplicidade de pagamento do mesmo mês/ano para um cliente
    boolean existsByClienteIdAndAnoAndMesPago(Integer clienteId, Integer ano, Integer mesPago);

    boolean existsByClienteId(Integer clienteId);
}

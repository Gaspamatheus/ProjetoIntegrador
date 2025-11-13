package com.example.ProjetoIntegradorweb.domian.service;

import com.example.ProjetoIntegradorweb.api.dto.garagemDTO;
import com.example.ProjetoIntegradorweb.api.dto.garagemRequestDTO;
import com.example.ProjetoIntegradorweb.domian.model.cliente;
import com.example.ProjetoIntegradorweb.domian.model.garagem;
import com.example.ProjetoIntegradorweb.domian.repository.ClienteRepository;
import com.example.ProjetoIntegradorweb.domian.repository.garagemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class garagemService {
    private final garagemRepository garagemRepo;
    private final ClienteRepository clienteRepo;

    public garagemService(garagemRepository garagemRepo, ClienteRepository clienteRepo) {
        this.garagemRepo = garagemRepo;
        this.clienteRepo = clienteRepo;
    }

    @Transactional
    public garagemDTO criar(garagemRequestDTO req){
        cliente cliente = req.clienteId() != null
                ? clienteRepo.findById(req.clienteId())
                    .orElseThrow(()-> new IllegalArgumentException("cliente não encontrado"))
                :null;

        if(cliente != null && garagemRepo.existsByClienteIdAndAnoAndMesPago(cliente.getId(), req.ano(), req.mesPago())){
            throw new IllegalArgumentException("Pagamento desse mês/ano já registrado para este cliente.");
        }
        garagem g = new garagem();
        g.setAno(req.ano());
        g.setMesPago(req.mesPago());
        g.setGaragemFechada(req.garagemFechada());
        g.setValorGaragem(req.valorGaragem());
        g.setCliente(cliente);

        garagem salva = garagemRepo.save(g);
        return garagemDTO.fromEntity(salva);
    }
}

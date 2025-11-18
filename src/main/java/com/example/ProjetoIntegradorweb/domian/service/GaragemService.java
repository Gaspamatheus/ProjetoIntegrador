package com.example.ProjetoIntegradorweb.domian.service;



import com.example.ProjetoIntegradorweb.api.dto.garagemDTO;
import com.example.ProjetoIntegradorweb.api.dto.garagemRequestDTO;
import com.example.ProjetoIntegradorweb.application.garagemMapper;
import com.example.ProjetoIntegradorweb.domian.model.cliente;
import com.example.ProjetoIntegradorweb.domian.model.garagem;
import com.example.ProjetoIntegradorweb.domian.repository.ClienteRepository;
import com.example.ProjetoIntegradorweb.domian.repository.garagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class GaragemService {

    private final garagemRepository garagemRepo;
    private final ClienteRepository clienteRepo;

    public GaragemService(garagemRepository garagemRepo, ClienteRepository clienteRepo) {
        this.garagemRepo = garagemRepo;
        this.clienteRepo = clienteRepo;
    }

    @Transactional
    public garagemDTO criar(garagemRequestDTO req) {
        cliente cliente = resolveCliente(req.clienteId());
        if (cliente != null && garagemRepo.existsByClienteIdAndAnoAndMesPago(cliente.getId(), req.ano(), req.mesPago())) {
            throw new IllegalArgumentException("Pagamento desse mês/ano já registrado para este cliente.");
        }
        garagem salvo = garagemRepo.save(garagemMapper.toEntity(req, cliente));
        return garagemDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public garagemDTO buscarPorId(Integer id) {
        garagem g = garagemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Garagem não encontrada"));
        return garagemDTO.fromEntity(g);
    }

    @Transactional(readOnly = true)
    public List<garagemDTO> listar() {
        return garagemRepo.findAll().stream().map(garagemDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<garagemDTO> listarPorCliente(Integer clienteId) {
        return garagemRepo.findByCienteId(clienteId).stream().map(garagemDTO::fromEntity).toList();
    }

    @Transactional
    public garagemDTO atualizar(Integer id, garagemRequestDTO req) {
        garagem entity = garagemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Garagem não encontrada"));

        Integer novoAno = req.ano() != null ? req.ano() : entity.getAno();
        Integer novoMes = req.mesPago() != null ? req.mesPago() : entity.getMesPago();

        Integer oldClienteId = entity.getCliente() != null ? entity.getCliente().getId() : null;
        cliente novoCliente = (req.clienteId() != null) ? resolveCliente(req.clienteId()) : entity.getCliente();
        Integer novoClienteId = novoCliente != null ? novoCliente.getId() : null;

        boolean mudouChave = !Objects.equals(novoAno, entity.getAno())
                || !Objects.equals(novoMes, entity.getMesPago())
                || !Objects.equals(novoClienteId, oldClienteId);

        if (mudouChave && novoClienteId != null
                && garagemRepo.existsByClienteIdAndAnoAndMesPago(novoClienteId, novoAno, novoMes)) {
            throw new IllegalArgumentException("Pagamento desse mês/ano já registrado para este cliente.");
        }

        garagemMapper.updateEntity(req, entity, novoCliente);
        return garagemDTO.fromEntity(entity); // dirty checking
    }

    @Transactional
    public void deletar(Integer id) {
        if (!garagemRepo.existsById(id)) throw new IllegalArgumentException("Garagem não encontrada");
        garagemRepo.deleteById(id);
    }

    private cliente resolveCliente(Integer clienteId) {
        if (clienteId == null) return null;
        return clienteRepo.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }
}

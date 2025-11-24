package com.projeto.integrador.service;

import com.projeto.integrador.dto.GaragemDTO;
import com.projeto.integrador.mapper.GaragemMapper;
import com.projeto.integrador.model.Cliente;
import com.projeto.integrador.model.Garagem;
import com.projeto.integrador.repository.ClienteRepository;
import com.projeto.integrador.repository.GaragemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GaragemService {

    private final GaragemRepository garagemRepository;
    private final ClienteRepository clienteRepository;
    private final GaragemMapper garagemMapper;

    @Transactional(readOnly = true)
    public List<GaragemDTO> findAll() {
        return garagemMapper.toDTOList(garagemRepository.findAll());
    }

    @Transactional(readOnly = true)
    public GaragemDTO findById(Integer id) {
        Garagem garagem = garagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garagem não encontrada com ID: " + id));
        return garagemMapper.toDTO(garagem);
    }

    @Transactional(readOnly = true)
    public List<GaragemDTO> findByClienteId(Integer clienteId) {
        return garagemMapper.toDTOList(garagemRepository.findByClienteId(clienteId));
    }

    @Transactional(readOnly = true)
    public List<GaragemDTO> findByAno(Integer ano) {
        return garagemMapper.toDTOList(garagemRepository.findByAno(ano));
    }

    @Transactional(readOnly = true)
    public List<GaragemDTO> findByAnoAndMes(Integer ano, Integer mes) {
        return garagemMapper.toDTOList(garagemRepository.findByAnoAndMesPago(ano, mes));
    }

    @Transactional
    public GaragemDTO create(GaragemDTO garagemDTO) {
        Cliente cliente = clienteRepository.findById(garagemDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + garagemDTO.getClienteId()));

        Garagem garagem = garagemMapper.toEntity(garagemDTO);
        garagem.setCliente(cliente);

        Garagem savedGaragem = garagemRepository.save(garagem);
        return garagemMapper.toDTO(savedGaragem);
    }

    @Transactional
    public GaragemDTO update(Integer id, GaragemDTO garagemDTO) {
        Garagem garagem = garagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garagem não encontrada com ID: " + id));

        Cliente cliente = clienteRepository.findById(garagemDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + garagemDTO.getClienteId()));

        garagemMapper.updateEntityFromDTO(garagemDTO, garagem);
        garagem.setCliente(cliente);

        Garagem updatedGaragem = garagemRepository.save(garagem);
        return garagemMapper.toDTO(updatedGaragem);
    }

    @Transactional
    public void delete(Integer id) {
        if (!garagemRepository.existsById(id)) {
            throw new RuntimeException("Garagem não encontrada com ID: " + id);
        }
        garagemRepository.deleteById(id);
    }
}
package com.projeto.integrador.service;

import com.projeto.integrador.dto.ClienteDTO;
import com.projeto.integrador.mapper.ClienteMapper;
import com.projeto.integrador.model.Cliente;
import com.projeto.integrador.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return clienteMapper.toDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByNome(String nome) {
        return clienteMapper.toDTOList(clienteRepository.findByNomeContainingIgnoreCase(nome));
    }

    @Transactional
    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(savedCliente);
    }

    @Transactional
    public ClienteDTO update(Integer id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        clienteMapper.updateEntityFromDTO(clienteDTO, cliente);
        Cliente updatedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(updatedCliente);
    }

    @Transactional
    public void delete(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
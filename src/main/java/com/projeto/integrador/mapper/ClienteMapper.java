package com.projeto.integrador.mapper;

import com.projeto.integrador.dto.ClienteDTO;
import com.projeto.integrador.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "garagens", ignore = true)
    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDTO(Cliente entity);

    List<ClienteDTO> toDTOList(List<Cliente> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garagens", ignore = true)
    void updateEntityFromDTO(ClienteDTO dto, @MappingTarget Cliente entity);
}
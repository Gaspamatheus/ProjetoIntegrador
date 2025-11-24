package com.projeto.integrador.mapper;

import com.projeto.integrador.dto.GaragemDTO;
import com.projeto.integrador.model.Garagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GaragemMapper {

    GaragemMapper INSTANCE = Mappers.getMapper(GaragemMapper.class);

    @Mapping(source = "clienteId", target = "cliente.id")
    Garagem toEntity(GaragemDTO dto);

    @Mapping(source = "cliente.id", target = "clienteId")
    GaragemDTO toDTO(Garagem entity);

    List<GaragemDTO> toDTOList(List<Garagem> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "clienteId", target = "cliente.id")
    void updateEntityFromDTO(GaragemDTO dto, @MappingTarget Garagem entity);
}
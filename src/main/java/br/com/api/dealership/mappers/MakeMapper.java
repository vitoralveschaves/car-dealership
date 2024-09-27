package br.com.api.dealership.mappers;

import br.com.api.dealership.dtos.MakeRequestDto;
import br.com.api.dealership.dtos.MakeResponseDto;
import br.com.api.dealership.entities.Make;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MakeMapper {
    Make dtoToEntity(MakeRequestDto request);
    MakeResponseDto entityToDto(Make entity);
}

package br.com.api.dealership.services;

import br.com.api.dealership.dtos.MakeRequestDto;
import br.com.api.dealership.dtos.MakeResponseDto;

import java.util.List;

public interface MakeService {
    MakeResponseDto register(MakeRequestDto request);
    List<MakeResponseDto> getAll();
}

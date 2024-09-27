package br.com.api.dealership.services.impl;

import br.com.api.dealership.dtos.MakeRequestDto;
import br.com.api.dealership.dtos.MakeResponseDto;
import br.com.api.dealership.mappers.MakeMapper;
import br.com.api.dealership.repositories.MakeRepository;
import br.com.api.dealership.services.MakeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;
    private final MakeMapper makeMapper;

    public MakeServiceImpl(MakeRepository makeRepository, MakeMapper makeMapper) {
        this.makeRepository = makeRepository;
        this.makeMapper = makeMapper;
    }

    @Override
    public MakeResponseDto register(MakeRequestDto request) {
        var make = makeMapper.dtoToEntity(request);
        var makeSaved = makeRepository.save(make);
        return makeMapper.entityToDto(makeSaved);
    }

    @Override
    public List<MakeResponseDto> getAll() {
        var makeList = makeRepository.findAll();
        return makeList.stream().map(makeMapper::entityToDto).toList();
    }
}

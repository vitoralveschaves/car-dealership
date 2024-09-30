package br.com.api.dealership.controllers;

import br.com.api.dealership.dtos.MakeRequestDto;
import br.com.api.dealership.dtos.MakeResponseDto;
import br.com.api.dealership.services.MakeService;
import br.com.api.dealership.swagger.MakeControllerSwagger;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/make")
public class MakeController implements MakeControllerSwagger {

    private final MakeService makeService;

    public MakeController(MakeService makeService) {
        this.makeService = makeService;
    }

    @PostMapping
    public ResponseEntity<MakeResponseDto> save(@RequestBody @Valid  MakeRequestDto request) {
        var savedMake = makeService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMake);
    }

    @GetMapping
    public ResponseEntity<List<MakeResponseDto>> getAll() {
        var listMake = makeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listMake);
    }
}

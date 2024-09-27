package br.com.api.dealership.dtos;

import br.com.api.dealership.entities.Make;
import br.com.api.dealership.enums.CarModel;

import java.math.BigDecimal;
import java.util.UUID;

public record CarResponseDto(UUID id,
                             String name,
                             Integer year,
                             BigDecimal price,
                             Boolean used,
                             CarModel model,
                             Make make) {
}

package br.com.api.dealership.dtos;

import java.util.List;

public record PaginationResponseDto(Integer page,
                                    Integer pageSize,
                                    Integer totalPages,
                                    Boolean first,
                                    Boolean last,
                                    List<CarResponseDto> cars) {
}

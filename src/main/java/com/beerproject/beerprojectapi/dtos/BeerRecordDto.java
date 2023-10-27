package com.beerproject.beerprojectapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BeerRecordDto(@NotBlank String name, @NotBlank String type,
                            @NotBlank String origin, @NotNull BigDecimal price, String imgUrl, BigDecimal rating) {
}

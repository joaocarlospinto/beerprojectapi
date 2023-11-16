package com.beerproject.beerprojectapi.dtos;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BeerRecordDto(@NotBlank(message = "{name.not.blank}") String name,
                            @NotBlank(message = "{type.not.blank}") String type,
                            @NotBlank(message = "{origin.not.blank}") String origin,
                            @NotNull(message = "{price.not.null}") BigDecimal price,
                            @NotNull(message = "{rating.not.null}") BigDecimal rating) {
}

package com.beerproject.beerprojectapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record TypeRecordDto(@NotBlank String type) {
}

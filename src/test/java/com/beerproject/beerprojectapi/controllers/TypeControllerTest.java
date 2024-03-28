package com.beerproject.beerprojectapi.controllers;

import com.beerproject.beerprojectapi.Exceptions.DuplicatedTypeException;
import com.beerproject.beerprojectapi.dtos.TypeRecordDto;
import com.beerproject.beerprojectapi.models.TypeModel;
import com.beerproject.beerprojectapi.repositories.TypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class TypeControllerTest {

    @Autowired
    @InjectMocks
    private TypeController typeController;

    @Mock
    TypeRepository typeRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save Type Successfully")
    void saveType() {

        TypeRecordDto type = new TypeRecordDto("PILSEN");

        ResponseEntity<Object> responseEntity = typeController.saveType(type);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Should return Duplicated Exception")
    void saveTypeDuplicated() {
        TypeModel type = new TypeModel();
        type.setId(1L);
        type.setType("PILSEN");
        when(typeRepository.findByType("PILSEN")).thenReturn(Optional.of(type));
        TypeRecordDto newType = new TypeRecordDto("PILSEN");
        assertThrows(DuplicatedTypeException.class, () -> typeController.saveType(newType));
    }


}
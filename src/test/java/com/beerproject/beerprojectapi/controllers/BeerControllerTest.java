package com.beerproject.beerprojectapi.controllers;

import com.beerproject.beerprojectapi.Exceptions.DuplicatedBeerException;
import com.beerproject.beerprojectapi.dtos.BeerRecordDto;
import com.beerproject.beerprojectapi.models.BeerModel;
import com.beerproject.beerprojectapi.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    @InjectMocks
    private BeerController beerController;

    @Mock
    BeerRepository beerRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return Duplicated Exception")
    void saveBeerDuplicated() throws Exception {
        String name = "CERVEJA";
        BigDecimal price = new BigDecimal("2.90");
        BigDecimal rating = new BigDecimal("1.00");
        BeerModel beerModel = new BeerModel(1L, name,  "PILSEN", "Teste", price, rating, "test.jpg");
        List<BeerModel> beerList = new ArrayList<>();
        beerList.add(beerModel);
        BeerRecordDto beer = new BeerRecordDto(name, "PILSEN", "Teste", price, rating, null);
        when(beerRepository.findByName(name)).thenReturn(beerList);
        // DuplicatedBeerException duplicatedBeerException  =
        assertThrows(DuplicatedBeerException.class, () -> beerController.saveBeer(beer));
    }

    @Test
    @DisplayName("Should return Duplicated Exception with message 'Beer already Exists'")
    void saveBeerDuplicatedMsg() throws Exception {
        String name = "CERVEJA";
        BigDecimal price = new BigDecimal("2.90");
        BigDecimal rating = new BigDecimal("1.00");
        BeerModel beerModel = new BeerModel(1L, name,  "PILSEN", "Teste", price, rating, "test.jpg");
        List<BeerModel> beerList = new ArrayList<>();
        beerList.add(beerModel);
        BeerRecordDto beer = new BeerRecordDto(name, "PILSEN", "Teste", price, rating, null);
        when(beerRepository.findByName(name)).thenReturn(beerList);
        DuplicatedBeerException duplicatedBeerException  =
        assertThrows(DuplicatedBeerException.class, () -> beerController.saveBeer(beer));
        assertEquals("Beer already Exists", duplicatedBeerException.getMessage());
    }

    @Test
    @DisplayName("Should save beer successfully")
    void saveBeerDuplicatedSuccess() throws Exception {
        String name = "CERVEJA";
        BigDecimal price = new BigDecimal("2.90");
        BigDecimal rating = new BigDecimal("1.00");
        BeerModel beerModel = new BeerModel(1L, name,  "PILSEN", "Teste", price, rating, "test.jpg");
        List<BeerModel> beerList = new ArrayList<>();
        beerList.add(beerModel);
        BeerRecordDto beer = new BeerRecordDto("AURORA", "PILSEN", "Teste", price, rating, null);
        when(beerRepository.findByName(name)).thenReturn(beerList);
        ResponseEntity<Object> responseEntity = beerController.saveBeer(beer);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Should get beer id 1L")
    void getBeerSuccess() {
        String name = "CERVEJA";
        BigDecimal price = new BigDecimal("2.90");
        BigDecimal rating = new BigDecimal("1.00");
        BeerModel beerModel = new BeerModel(1L, name,  "PILSEN", "Teste", price, rating, "test.jpg");
        when(beerRepository.findById(1L)).thenReturn(Optional.of(beerModel));
        ResponseEntity<Object> responseEntity = beerController.getOneBeer(1L);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
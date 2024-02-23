package com.beerproject.beerprojectapi.repositories;

import com.beerproject.beerprojectapi.dtos.BeerRecordDto;
import com.beerproject.beerprojectapi.models.BeerModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Should return beer Successfully")
    void findByNameSuccess() {

        String name = "CERVEJA";
        BigDecimal price = new BigDecimal("2.90");
        BigDecimal rating = new BigDecimal("1.00");
        BeerRecordDto beer = new BeerRecordDto(name, "PILSEN", "Teste", price, rating, null);
        this.createBeer(beer);

        List<BeerModel> result =  this.beerRepository.findByName(name);
        System.out.println(result.get(0).getName());

        assertThat(result.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Should not get beer")
    void findByNameNotSuccess() {

        String name = "CERVEJA";

        List<BeerModel> result =  this.beerRepository.findByName(name);

        assertThat(result.isEmpty()).isTrue();
    }

    private BeerModel createBeer(BeerRecordDto data) {
        BeerModel beer = new BeerModel(data);
        this.entityManager.persist(beer);
        return beer;
    }
}
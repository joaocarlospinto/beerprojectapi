package com.beerproject.beerprojectapi.repositories;

import com.beerproject.beerprojectapi.models.BeerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<BeerModel, Long> {
}

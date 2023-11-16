package com.beerproject.beerprojectapi.repositories;

import com.beerproject.beerprojectapi.models.BeerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<BeerModel, Long> {
    List<BeerModel> findByName(String name);
}

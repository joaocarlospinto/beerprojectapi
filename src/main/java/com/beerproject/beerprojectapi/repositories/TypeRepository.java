package com.beerproject.beerprojectapi.repositories;

import com.beerproject.beerprojectapi.models.TypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<TypeModel, Long> {
    Optional<TypeModel> findByType(String type);
}

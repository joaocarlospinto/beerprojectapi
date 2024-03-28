package com.beerproject.beerprojectapi.repositories;

import com.beerproject.beerprojectapi.dtos.TypeRecordDto;
import com.beerproject.beerprojectapi.models.TypeModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TypeRepositoryTest {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Should return Type Successfully")
    void findByType() {
        TypeRecordDto type = new TypeRecordDto("WEISS");
        createType(type);
        Optional<TypeModel> typeReturned = this.typeRepository.findByType("WEISS");
        assertThat(typeReturned.isPresent());
        assertEquals("WEISS", typeReturned.get().getType());
    }

    private TypeModel createType(TypeRecordDto type) {
        TypeModel newType = new TypeModel(type);
        this.entityManager.persist(newType);
        return newType;
    }
}
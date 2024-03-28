package com.beerproject.beerprojectapi.controllers;

import com.beerproject.beerprojectapi.Exceptions.DuplicatedTypeException;
import com.beerproject.beerprojectapi.Exceptions.TypeNotFoundException;
import com.beerproject.beerprojectapi.dtos.TypeRecordDto;
import com.beerproject.beerprojectapi.models.TypeModel;
import com.beerproject.beerprojectapi.repositories.TypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200/",
        "https://joaocarlospinto.github.io/beerprojectfrontend/",
        "https://joaocarlospinto.github.io/beerproject/",
        "https://joaocarlospinto.github.io/"
})
@RestController
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @PostMapping("/typesapi")
    public ResponseEntity<Object> saveType(@RequestBody @Valid TypeRecordDto typeRecordDto) throws DuplicatedTypeException {
        var typeModel = new TypeModel();
        BeanUtils.copyProperties(typeRecordDto, typeModel);

        Optional<TypeModel> typeO = typeRepository.findByType(typeModel.getType());

        if(!typeO.isEmpty()) {
            throw new DuplicatedTypeException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(typeRepository.save(typeModel));
    }

    @GetMapping("/typesapi")
    public ResponseEntity<List<TypeModel>> getAllTypes() {
        List<TypeModel> typeList = typeRepository.findAll();
        if (!typeList.isEmpty()) {
            for(TypeModel type : typeList){
                Long id = type.getId();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(typeList);
    }

    @GetMapping("/typesapi/{id}")
    public ResponseEntity<Object> getOneType(@PathVariable(value="id") Long id) throws TypeNotFoundException {
        Optional<TypeModel> typeO = typeRepository.findById(id);
        if(typeO.isEmpty()) {
            throw new TypeNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(typeO.get());
    }


    @DeleteMapping("/typesapi/{id}")
    public ResponseEntity<Object> deleteType(@PathVariable(value="id")Long id)  throws TypeNotFoundException {
        Optional<TypeModel> typeO = typeRepository.findById(id);
        if(typeO.isEmpty()) {
            throw new TypeNotFoundException();
        }
        typeRepository.delete(typeO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Type deleted Successfully");
    }

}

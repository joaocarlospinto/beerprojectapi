package com.beerproject.beerprojectapi.controllers;


import com.beerproject.beerprojectapi.dtos.BeerRecordDto;
import com.beerproject.beerprojectapi.models.BeerModel;
import com.beerproject.beerprojectapi.repositories.BeerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class BeerController {

    @Autowired
    BeerRepository beerRepository;

    @PostMapping("/beersapi")
    public ResponseEntity<Object> saveBeer(@RequestBody @Valid BeerRecordDto beerRecordDto) {
        var beerModel = new BeerModel();
        BeanUtils.copyProperties(beerRecordDto, beerModel);

        List<BeerModel> beerO = beerRepository.findByName(beerModel.getName());
        if(!beerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Beer already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(beerRepository.save(beerModel));
    }

    @GetMapping("/beersapi")
    public ResponseEntity<List<BeerModel>> getAllBeers(){
        List<BeerModel> beersList = beerRepository.findAll();
        if (!beersList.isEmpty()) {
            for(BeerModel beer : beersList){
                Long id = beer.getId();
                beer.add(linkTo(methodOn(BeerController.class).getOneBeer(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(beersList);
    }

    @GetMapping("/beersapi/{id}")
    public ResponseEntity<Object> getOneBeer(@PathVariable(value="id") Long id){
        Optional<BeerModel> beerO = beerRepository.findById(id);
        if(beerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beer not found");
        }
        beerO.get().add(linkTo(methodOn(BeerController.class).getAllBeers()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(beerO.get());
    }

    @PutMapping("/beersapi/{id}")
    public ResponseEntity<Object> updateBeer(@PathVariable(value="id") Long id,
                                             @RequestBody @Valid BeerRecordDto beerRecordDto) {
        Optional<BeerModel> beerO = beerRepository.findById(id);
        if(beerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beer not found");
        }
        var beerModel = beerO.get();
        BeanUtils.copyProperties(beerRecordDto, beerModel);
        return ResponseEntity.status(HttpStatus.OK).body(beerRepository.save(beerModel));
    }

    @DeleteMapping("/beersapi/{id}")
    public ResponseEntity<Object> deleteBeer(@PathVariable(value="id")Long id) {
        Optional<BeerModel> beerO = beerRepository.findById(id);
        if(beerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beer not found");
        }
        beerRepository.delete(beerO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Beer deleted Successfully");
    }




}

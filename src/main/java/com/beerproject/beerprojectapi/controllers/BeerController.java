package com.beerproject.beerprojectapi.controllers;


import com.beerproject.beerprojectapi.Exceptions.BeerNotFoundException;
import com.beerproject.beerprojectapi.Exceptions.DuplicatedBeerException;
import com.beerproject.beerprojectapi.dtos.BeerRecordDto;
import com.beerproject.beerprojectapi.models.BeerModel;
import com.beerproject.beerprojectapi.repositories.BeerRepository;
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
public class BeerController {

    @Autowired
    BeerRepository beerRepository;


    @PostMapping("/beersapi")
    public ResponseEntity<Object> saveBeer(@RequestBody @Valid BeerRecordDto beerRecordDto) throws DuplicatedBeerException {
        var beerModel = new BeerModel();
        BeanUtils.copyProperties(beerRecordDto, beerModel);

        List<BeerModel> beerO = beerRepository.findByName(beerModel.getName());

        if(!beerO.isEmpty()) {
            throw new DuplicatedBeerException();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(beerRepository.save(beerModel));
    }

    @GetMapping("/beersapi")
    public ResponseEntity<List<BeerModel>> getAllBeers()  {
        List<BeerModel> beersList = beerRepository.findAll();
        if (!beersList.isEmpty()) {
            for(BeerModel beer : beersList){
                Long id = beer.getId();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(beersList);
    }

    @GetMapping("/beersapi/{id}")
    public ResponseEntity<Object> getOneBeer(@PathVariable(value="id") Long id) throws BeerNotFoundException{
        Optional<BeerModel> beerO = beerRepository.findById(id);
       if(beerO.isEmpty()) {
           throw new BeerNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(beerO.get());
    }

    @PutMapping("/beersapi/{id}")
    public BeerModel updateBeer(@PathVariable(value="id") Long id,
                                             @RequestBody @Valid BeerRecordDto beerRecordDto)  throws BeerNotFoundException {
        Optional<BeerModel> beerO = beerRepository.findById(id);
        if(beerO.isEmpty()) {
            throw new BeerNotFoundException();
        }

        return beerRepository.findById(id)
                .map(beer ->  {
                    beer.setName(beerRecordDto.name());
                    beer.setType(beerRecordDto.type());
                    beer.setOrigin(beerRecordDto.origin());
                    beer.setPrice(beerRecordDto.price());
                    beer.setRating(beerRecordDto.rating());
                    beer.setImage(beerRecordDto.image());
                    return beerRepository.save(beer);
                })
                .orElseGet(() -> {
                    BeerModel newBeer = new BeerModel();
                    newBeer.setName(beerRecordDto.name());
                    newBeer.setOrigin(beerRecordDto.origin());
                    newBeer.setPrice(beerRecordDto.price());
                    newBeer.setRating(beerRecordDto.rating());
                    newBeer.setImage(beerRecordDto.image());
                    newBeer.setType(beerRecordDto.type());
                    newBeer.setId(id);
                    return beerRepository.save(newBeer);
                });

    }

    @DeleteMapping("/beersapi/{id}")
    public ResponseEntity<Object> deleteBeer(@PathVariable(value="id")Long id)  throws BeerNotFoundException {
        Optional<BeerModel> beerO = beerRepository.findById(id);
        if(beerO.isEmpty()) {
            throw new BeerNotFoundException();
        }
        beerRepository.delete(beerO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Beer deleted Successfully");
    }




}

package com.beerproject.beerprojectapi.models;

import com.beerproject.beerprojectapi.dtos.BeerRecordDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="BEER_DB")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class BeerModel  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String name;
    private String type;
    private String origin;
    private BigDecimal price;
    private BigDecimal rating;
    private String image;

    public BeerModel(BeerRecordDto beer) {
        this.name = beer.name();
        this.origin = beer.origin();
        this.type = beer.type();
        this.price = beer.price();
        this.rating = beer.rating();
        this.image = beer.image();
    }
}

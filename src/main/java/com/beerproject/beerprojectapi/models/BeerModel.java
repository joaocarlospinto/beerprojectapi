package com.beerproject.beerprojectapi.models;

import com.beerproject.beerprojectapi.dtos.BeerRecordDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="BEER_DB")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BeerModel extends RepresentationModel<BeerModel> implements Serializable {
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

    public BeerModel(BeerRecordDto data) {
        this.name = data.name();
        this.type = data.type();
        this.origin = data.origin();
        this.price = data.price();
        this.rating = data.rating();
        this.image = data.image();
    }

    public BeerModel() {

    }

    public BeerModel(long id, String name, String type, String origin, BigDecimal price, BigDecimal rating, String image) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

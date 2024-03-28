package com.beerproject.beerprojectapi.models;

import com.beerproject.beerprojectapi.dtos.TypeRecordDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="TYPE_DB")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class TypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String type;

    public TypeModel(String type) {
        this.type = type;
    }

    public TypeModel(TypeRecordDto type) {
        this.type = type.type();
    }
}

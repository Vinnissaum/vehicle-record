package br.com.tinnova.vehiclesrecords.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Brand is mandatory")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @NotNull(message = "Production year is mandatory")
    @Column(name = "production_year")
    private Integer year;

    private String description;

    private Boolean isSold;
    @NotBlank(message = "Color is mandatory")
    private String color;

    @CreationTimestamp
    @Column(nullable = false,  columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime updatedAt;

}

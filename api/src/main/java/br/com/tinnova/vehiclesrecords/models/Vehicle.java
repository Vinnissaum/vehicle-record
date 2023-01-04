package br.com.tinnova.vehiclesrecords.models;

import jakarta.persistence.*;
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

    private String model;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Column(name = "production_year")
    private Integer year;

    private String description;

    private Boolean isSold;

    private String color;

    @CreationTimestamp
    @Column(nullable = false,  columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime updatedAt;

}

package br.com.tinnova.vehiclesrecords.dto;

import br.com.tinnova.vehiclesrecords.models.Brand;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VehicleDTO {

    private UUID id;

    private String model;

    private Brand brand;

    private Integer year;

    private String description;

    private Boolean isSold;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

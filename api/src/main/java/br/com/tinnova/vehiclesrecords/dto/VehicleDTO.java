package br.com.tinnova.vehiclesrecords.dto;

import br.com.tinnova.vehiclesrecords.models.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VehicleDTO {

    private UUID id;

    @NotBlank(message = "Modelo é obrigatório")
    private String model;

    @NotNull(message = "Marca é obrigatório")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @NotNull(message = "Ano de fabricação é obrigatório")
    private Integer year;

    private String description;

    @NotNull(message = "Está vendido é obrigatório")
    private Boolean isSold;
    @NotBlank(message = "Cor é obrigatório")
    private String color;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

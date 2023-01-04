package br.com.tinnova.vehiclesrecords.mappers;

import br.com.tinnova.vehiclesrecords.dto.VehicleDTO;
import br.com.tinnova.vehiclesrecords.models.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleMapper {

    public VehicleDTO toDTO(Vehicle entity) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(entity.getId());
        dto.setModel(entity.getModel());
        dto.setBrand(entity.getBrand());
        dto.setYear(entity.getYear());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setIsSold(entity.getIsSold());
        dto.setColor(entity.getColor());

        return dto;
    }

    public Vehicle toEntity(VehicleDTO dto) {
        Vehicle entity = new Vehicle();
        entity.setId(dto.getId());
        entity.setModel(dto.getModel());
        entity.setBrand(dto.getBrand());
        entity.setYear(dto.getYear());
        entity.setDescription(dto.getDescription());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setIsSold(dto.getIsSold());
        entity.setColor(dto.getColor());

        return entity;
    }

}

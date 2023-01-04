package br.com.tinnova.vehiclesrecords.services;

import br.com.tinnova.vehiclesrecords.dto.VehicleDTO;
import br.com.tinnova.vehiclesrecords.exceptions.ResourceNotFoundException;
import br.com.tinnova.vehiclesrecords.mappers.VehicleMapper;
import br.com.tinnova.vehiclesrecords.models.Vehicle;
import br.com.tinnova.vehiclesrecords.repositories.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleService {

    private VehicleRepository repository;

    private VehicleMapper mapper;

    private static final String NOT_FOUND = "Veículo de id '%s' não encontrado";

    @Transactional
    public List<VehicleDTO> findAll() {
        List<Vehicle> vehicleList = repository.findAll();

        return vehicleList.stream().map(mapper::toDTO).toList();
    }

    @Transactional
    public List<VehicleDTO> filterByBrandYearAndColor(String brand, Integer year, String color) {
        List<Vehicle> vehicleList = repository.filterByBrandYearAndColor(brand, year, color);

        return vehicleList.stream().map(mapper::toDTO).toList();
    }

    @Transactional
    public VehicleDTO findById(UUID id) {
        Vehicle entity = repository.findById(id).orElseThrow( //
                () -> new ResourceNotFoundException(String.format(NOT_FOUND, id)) //
        );

        return mapper.toDTO(entity);
    }

    @Transactional
    public VehicleDTO create(VehicleDTO dto) {
        Vehicle entity = repository.save(mapper.toEntity(dto));

        return mapper.toDTO(entity);
    }

    @Transactional
    public VehicleDTO update(UUID id, VehicleDTO dto) {
        Vehicle entity = repository.findById(id).orElseThrow( //
                () -> new ResourceNotFoundException(String.format(NOT_FOUND, id)) //
        );
        BeanUtils.copyProperties(dto, entity, "id", "createdAt");

        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public VehicleDTO partialUpdate(UUID id, Map<String, Object> attributes) {
        Vehicle entity = repository.findById(id).orElseThrow( //
                () -> new ResourceNotFoundException(String.format(NOT_FOUND, id)) //
        );

        merge(entity, attributes);
        entity = repository.save(entity);

        return mapper.toDTO(entity);
    }

    public void deleteById(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND, id));
        }
    }

    private void merge(Vehicle entity, Map<String, Object> attributes) {
        ObjectMapper objectMapper = new ObjectMapper();
        Vehicle updatedVehicle = objectMapper.convertValue(attributes, Vehicle.class);

        attributes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Vehicle.class, key);
            if (field != null) {
                field.setAccessible(true);
                Object newValue = ReflectionUtils.getField(field, updatedVehicle);
                ReflectionUtils.setField(field, entity, newValue);
            }
        });
    }

}

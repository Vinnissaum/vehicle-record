package br.com.tinnova.vehiclesrecords.controllers;

import br.com.tinnova.vehiclesrecords.dto.VehicleDTO;
import br.com.tinnova.vehiclesrecords.services.VehicleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/veiculos")
@AllArgsConstructor
public class VehicleController {

    private VehicleService service;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> index() {
        List<VehicleDTO> list = service.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<VehicleDTO>> filterByBrandYearAndColor(@RequestParam("marca") String brand,
                                                                      @RequestParam("ano") Integer year,
                                                                      @RequestParam("cor") String color) {
        List<VehicleDTO> list = service.filterByBrandYearAndColor(brand, year, color);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> show(@PathVariable @NotNull UUID id) {
        VehicleDTO vehicleDTO = service.findById(id);

        return ResponseEntity.ok(vehicleDTO);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> store(@RequestBody @Valid VehicleDTO dto) {
        VehicleDTO vehicleDTO = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(vehicleDTO.getId()).toUri();

        return ResponseEntity.created(location).body(vehicleDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable @NotNull UUID id, @RequestBody @Valid VehicleDTO dto) {
        VehicleDTO vehicleDTO = service.update(id, dto);

        return ResponseEntity.ok(vehicleDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> partialUpdate(@PathVariable @NotNull UUID id, @RequestBody Map<String, Object> attributes) {
        VehicleDTO vehicleDTO = service.partialUpdate(id, attributes);

        return ResponseEntity.ok(vehicleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull UUID id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<VehicleDTO>> getNotSoldVehicles() {
        List<VehicleDTO> availableVehicles = service.getNotSoldVehicles();

        return ResponseEntity.ok(availableVehicles);
    }


    @GetMapping("/quantidade-por-decada")
    public ResponseEntity<Long> countByDecade(@RequestParam("decada") Integer decade) {
        Long count = service.countByDecade(decade);

        return ResponseEntity.ok(count);
    }

    @GetMapping("/quantidade-por-marca")
    public ResponseEntity<Long> countByBrand(@RequestParam("marca") String brand) {
        Long count = service.countByBrand(brand);

        return ResponseEntity.ok(count);
    }

    @GetMapping("/registros-da-semana")
    public ResponseEntity<List<VehicleDTO>> getWeekVehiclesRegisters() {
        List<VehicleDTO> registers = service.getWeekVehiclesRegisters();

        return ResponseEntity.ok(registers);
    }
}

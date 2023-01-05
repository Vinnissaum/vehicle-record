package br.com.tinnova.vehiclesrecords.services;

import br.com.tinnova.vehiclesrecords.dto.VehicleDTO;
import br.com.tinnova.vehiclesrecords.exceptions.ResourceNotFoundException;
import br.com.tinnova.vehiclesrecords.mappers.VehicleMapper;
import br.com.tinnova.vehiclesrecords.models.Brand;
import br.com.tinnova.vehiclesrecords.models.Vehicle;
import br.com.tinnova.vehiclesrecords.repositories.VehicleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository repository;

    @Mock
    private VehicleMapper mapper;

    @InjectMocks
    private VehicleService service;

    private Vehicle vehicle;

    @Before
    public void setUp() {
        this.vehicle = new Vehicle();
        this.vehicle.setBrand(Brand.BMW);
        this.vehicle.setYear(2023);
        this.vehicle.setCreatedAt(LocalDateTime.now());
        this.vehicle.setModel("M2 Competition");
        this.vehicle.setColor("Preta");
        this.vehicle.setIsSold(false);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    public void shouldReturnAllRegisteredVehicles_WhenCallsFindAll() {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());

        when(repository.findAll()).thenReturn(List.of(vehicle));
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        List<VehicleDTO> list = service.findAll();

        assertThat(list.size()).isEqualTo(1);
        verify(repository).findAll();
        verify(mapper).toDTO(vehicle);
    }

    @Test
    public void shouldReturnSpecificVehicle_WhenCallsFindById() {
        UUID uuid = UUID.randomUUID();
        vehicle.setId(uuid);
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());

        when(repository.findById(uuid)).thenReturn(Optional.of(vehicle));
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        VehicleDTO result = service.findById(uuid);

        assertThat(result).isInstanceOf(VehicleDTO.class);
        assertThat(result.getBrand()).isEqualTo(Brand.BMW);
        verify(repository).findById(uuid);
        verify(mapper).toDTO(vehicle);
    }

    @Test
    public void shouldThrowsAnException_WhenFindByIdReturnNull() {
        UUID wrongUUID = UUID.randomUUID();

        Throwable exception = catchThrowable(() -> service.findById(wrongUUID));

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
        verify(repository).findById(wrongUUID);
    }

    @Test
    public void shouldFilterByBrandYearAndColor() {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setYear(vehicle.getYear());
        dto.setColor(vehicle.getColor());

        when(repository.filterByBrandYearAndColor(Brand.BMW.toString(), 2023, "Preta")) //
                .thenReturn(List.of(vehicle));//
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        List<VehicleDTO> list = service.filterByBrandYearAndColor(Brand.BMW.toString(), 2023, "Preta");

        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getBrand()).isEqualTo(Brand.BMW);
        verify(repository).filterByBrandYearAndColor(Brand.BMW.toString(), 2023, "Preta");
        verify(mapper).toDTO(vehicle);
    }

    @Test
    public void shouldFilterByBrandYearAndColor_AndReturnNull() {
        VehicleDTO dto = new VehicleDTO();
        dto.setBrand(vehicle.getBrand());
        dto.setYear(vehicle.getYear());
        dto.setColor(vehicle.getColor());

        List<VehicleDTO> list = service.filterByBrandYearAndColor(Brand.BMW.toString(), 2023, "Preta");

        assertThat(list.size()).isEqualTo(0);
        verify(repository).filterByBrandYearAndColor(Brand.BMW.toString(), 2023, "Preta");
    }

    @Test
    public void shouldCreateVehicle() {
        VehicleDTO dto = new VehicleDTO();
        dto.setBrand(vehicle.getBrand());
        dto.setYear(vehicle.getYear());
        dto.setColor(vehicle.getColor());

        when(repository.save(vehicle)).thenReturn(vehicle);
        when(mapper.toEntity(dto)).thenReturn(vehicle);
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        VehicleDTO result = service.create(dto);

        assertThat(result).isNotNull().isInstanceOf(VehicleDTO.class);
        verify(repository).save(vehicle);
        verify(mapper).toDTO(vehicle);
        verify(mapper).toEntity(dto);
    }

    @Test
    public void shouldUpdateVehicle() {
        UUID id = UUID.randomUUID();
        vehicle.setId(id);
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setYear(vehicle.getYear());
        dto.setColor(vehicle.getColor());

        when(repository.findById(id)).thenReturn(Optional.of(vehicle));
        when(repository.save(vehicle)).thenReturn(vehicle);
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        VehicleDTO result = service.update(id, dto);

        assertThat(result).isNotNull().isInstanceOf(VehicleDTO.class);
        verify(repository).findById(id);
        verify(repository).save(vehicle);
        verify(mapper).toDTO(vehicle);
    }

    @Test
    public void shouldThrowAnException_WhenFindByIdReturnNullOnUpdateVehicle() {
        UUID id = UUID.randomUUID();
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setYear(vehicle.getYear());
        dto.setColor(vehicle.getColor());

        Throwable exception = catchThrowable(() -> service.update(id, dto));

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
        verify(repository).findById(id);
    }

    @Test
    public void shouldPartialUpdateVehicle() {
        UUID id = UUID.randomUUID();
        vehicle.setId(id);
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        Map<String, Object> attr = new HashMap<>();
        attr.put("brand", Brand.AUDI);

        when(repository.findById(id)).thenReturn(Optional.of(vehicle));
        when(repository.save(vehicle)).thenReturn(vehicle);
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        VehicleDTO result = service.partialUpdate(id, attr);

        assertThat(result).isEqualTo(dto);
        verify(repository).findById(id);
        verify(repository).save(vehicle);
        verify(mapper).toDTO(vehicle);
    }

    @Test
    public void shouldThrowAnException_WhenFindByIdReturnNullOnPartialUpdateVehicle() {
        UUID id = UUID.randomUUID();
        Map<String, Object> attr = new HashMap<>();

        Throwable exception = catchThrowable(() -> service.partialUpdate(id, attr));

        assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
        verify(repository).findById(id);
    }

    @Test
    public void shouldDeleteById() {
        UUID id = UUID.randomUUID();
        vehicle.setId(id);

        service.deleteById(id);

        verify(repository).deleteById(id);
    }

    @Test
    public void shouldGetNotSoldVehicles() {
        vehicle.setIsSold(false);
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setYear(vehicle.getYear());
        dto.setColor(vehicle.getColor());
        dto.setIsSold(vehicle.getIsSold());
        when(repository.findByIsSoldFalse()).thenReturn(List.of(vehicle));
        when(mapper.toDTO(vehicle)).thenReturn(dto);

        List<VehicleDTO> vehicles = service.getNotSoldVehicles();

        assertThat(vehicles.size()).isEqualTo(1);
        assertThat(vehicles.get(0).getIsSold()).isFalse();
        verify(repository).findByIsSoldFalse();
        verify(mapper).toDTO(vehicle);
    }

    @Test
    public void shouldCountByDecade() {
        when(repository.countByDecade(2020)).thenReturn(1L);

        Long count = service.countByDecade(2020);

        assertThat(count).isEqualTo(1);
        verify(repository).countByDecade(2020);
    }

    @Test
    public void shouldCountByBrand() {
        when(repository.countByBrandIgnoreCase("bmw")).thenReturn(1L);

        Long count = service.countByBrand("bmw");

        assertThat(count).isEqualTo(1);
        verify(repository).countByBrandIgnoreCase("bmw");
    }

}

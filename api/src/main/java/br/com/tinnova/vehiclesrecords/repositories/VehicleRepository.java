package br.com.tinnova.vehiclesrecords.repositories;

import br.com.tinnova.vehiclesrecords.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID>, VehicleRepositoryCustom {

    List<Vehicle> findByIsSoldFalse();

    List <Vehicle> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}

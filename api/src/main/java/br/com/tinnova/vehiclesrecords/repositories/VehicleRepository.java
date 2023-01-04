package br.com.tinnova.vehiclesrecords.repositories;

import br.com.tinnova.vehiclesrecords.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID>, VehicleRepositoryCustom {}

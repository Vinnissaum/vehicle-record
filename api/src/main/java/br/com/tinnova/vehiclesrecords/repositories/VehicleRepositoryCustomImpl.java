package br.com.tinnova.vehiclesrecords.repositories;

import br.com.tinnova.vehiclesrecords.models.QVehicle;
import br.com.tinnova.vehiclesrecords.models.Vehicle;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static br.com.tinnova.vehiclesrecords.models.QVehicle.vehicle;

class VehicleRepositoryCustomImpl implements VehicleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vehicle> filterByBrandYearAndColor(String brand, Integer year, String color) {

        return new JPAQuery<>(entityManager) //
                .select(vehicle) //
                .from(vehicle) //
                .where(vehicle.brand.stringValue().equalsIgnoreCase(brand) //
                        .and(vehicle.year.eq(year) //
                                .and(vehicle.color.equalsIgnoreCase(color)))) //
                .fetch();
    }

    @Override
    public Long countByDecade(Integer decade) {

        return new JPAQuery<>(entityManager) //
                .select(vehicle) //
                .from(vehicle) //
                .where(vehicle.year.between(decade, decade + 9))
                .stream().count();
    }

    @Override
    public Long countByBrandIgnoreCase(String brand) {

        return new JPAQuery<>(entityManager) //
                .select(vehicle) //
                .from(vehicle) //
                .where(vehicle.brand.stringValue().equalsIgnoreCase(brand)) //
                .stream().count();
    }
}

package br.com.tinnova.vehiclesrecords.repositories;

import br.com.tinnova.vehiclesrecords.models.Vehicle;

import java.util.List;

interface VehicleRepositoryCustom {

    List<Vehicle> filterByBrandYearAndColor(String brand, Integer year, String color);

    Long countByDecade(Integer decade);

    Long countByBrandIgnoreCase(String brand);

}

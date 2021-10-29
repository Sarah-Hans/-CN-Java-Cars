package com.cars.carsapp.dao;

import com.cars.carsapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

    Car findById(int id);

}

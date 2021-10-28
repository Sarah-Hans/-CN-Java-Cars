package com.cars.carsapp.dao;

import com.cars.carsapp.model.Car;

import java.util.List;

public interface CarDao {

    public List<Car> findAll();
    public Car findById(int id);
    public Car save(Car car);
    public void delete(Car car);
    public Car update(Car car);

}

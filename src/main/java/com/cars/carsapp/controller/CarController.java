package com.cars.carsapp.controller;

import com.cars.carsapp.dao.CarDao;
import com.cars.carsapp.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarDao carDao;

    // Récupère la liste des voitures
    @RequestMapping(value="/api/cars", method = RequestMethod.GET)
    public List<Car> carsList() {
        return carDao.findAll();
    }

    // Récupère une voiture en sélectionnant son id
    @GetMapping(value = "/api/cars/{id}")
    public Car oneCar(@PathVariable int id) {
        Car car = new Car(id, new String("Ford"), new String("Fiesta"), new String("Blue"), new String("https://wallsdesk.com/wp-content/uploads/2017/01/Ford-Fiesta-ST-Photos.jpg") );
        return carDao.findById(id);
    }

    // Ajout d'une voiture
    @PostMapping(value="/api/cars")
    public ResponseEntity<Void> addCar(@RequestBody Car car) {
        Car carAdded = carDao.save(car);
        if (carAdded == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //Delete one car
    @DeleteMapping(value = "/api/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        Car car = carDao.findById(id);
        carDao.delete(car);
    }

}

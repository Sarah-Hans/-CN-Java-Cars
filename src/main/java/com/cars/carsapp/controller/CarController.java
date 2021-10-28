package com.cars.carsapp.controller;

import com.cars.carsapp.dao.CarDao;
import com.cars.carsapp.model.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api( description = "API pour les opérations CRUD sur les voitures.")
@RestController
public class CarController {

    @Autowired
    private CarDao carDao;

    // Récupère la liste des voitures
    @ApiOperation(value = "Récupère la liste des voitures.")
    @RequestMapping(value="/api/cars", method = RequestMethod.GET)
    public List<Car> carsList() {
        return carDao.findAll();
    }

    // Récupère une voiture en sélectionnant son id
    @ApiOperation(value = "Récupère une voiture grâce à son ID.")
    @GetMapping(value = "/api/cars/{id}")
    public Car oneCar(@PathVariable int id) {
        Car car = new Car(id, new String("Ford"), new String("Fiesta"), new String("Blue"), new String("https://wallsdesk.com/wp-content/uploads/2017/01/Ford-Fiesta-ST-Photos.jpg") );
        return carDao.findById(id);
    }

    // Ajout d'une voiture
    @ApiOperation(value = "Ajoute une voiture")
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
    @ApiOperation(value = "Supprime une voiture")
    @DeleteMapping(value = "/api/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        Car car = carDao.findById(id);
        carDao.delete(car);
    }

    //Update car
    @ApiOperation(value = "Met à jour une voiture")
    @PostMapping(value = {"/api/cars/update"})
    public Car updateCar(@RequestBody Car car) {
        return carDao.update(car);
    }

}

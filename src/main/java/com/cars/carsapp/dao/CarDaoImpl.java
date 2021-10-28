package com.cars.carsapp.dao;

import com.cars.carsapp.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    public static List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(1, new String("Ford"), new String("Fiesta ST"), new String("Blue"), new String("https://wallsdesk.com/wp-content/uploads/2017/01/Ford-Fiesta-ST-Photos.jpg")));
        cars.add(new Car(2, new String("Ford"), new String("Kuga"), new String("White"), new String("https://smgmedia.blob.core.windows.net/images/113803/1024/ford-kuga-hatchback-43477a838984.jpg")));
        cars.add(new Car(3, new String("land Rover"), new String("Discovery Sport"), new String("Black"), new String("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2F2.bp.blogspot.com%2F-T3FRcwS1fVc%2FV2FiaqvZXVI%2FAAAAAAAAFBk%2FERs6v0jDyXYm7ULrFl95b3odSuUViv-SACLcB%2Fs1600%2FLand%252BRover%252BDiscovery%252BSport%252B2.0%252BTD4%252BSE%252BBlack%252BLabel-1.jpg&f=1&nofb=1")));
    }

    // Permet d'afficher toutes les voitures
    @Override
    public List<Car> findAll() {
        return cars;
    }

    // Permet d'afficher une voiture en récupérant son id
    @Override
    public Car findById(int id) {
        for (Car car : cars) {
            if(car.getId() == id){
                return car;
            }
        }
        return null;
    }

    // Permet de créer une voiture
    @Override
    public Car save(Car car) {
        cars.add(car);
        return car;
    }

    // Supprimer une voiture
    @Override
    public void delete(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == car.getId()) {
                cars.remove(i);
            }
        }
    }

    // Modifier une voiture
    @Override
    public Car update(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == car.getId()) {
                cars.set(i, car);
                return car;
            }
        }
        return null;
    }
}
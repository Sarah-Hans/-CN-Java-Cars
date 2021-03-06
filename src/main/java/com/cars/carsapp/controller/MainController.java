package com.cars.carsapp.controller;

import com.cars.carsapp.form.CarForm;
import com.cars.carsapp.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static List<Car> cars = new ArrayList<Car>();

    static {
        cars.add(new Car(1, "Ford", "Fiesta", "Blue", "https://wallsdesk.com/wp-content/uploads/2017/01/Ford-Fiesta-ST-Photos.jpg"));
        cars.add(new Car(2, "Ford", "Kuga", "White", "https://smgmedia.blob.core.windows.net/images/113803/1024/ford-kuga-hatchback-43477a838984.jpg"));
    }

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    // Cette méthode affiche l'index de l'app
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    // Cette méthode affiche la liste des voitures
    @RequestMapping(value = { "/carsList" }, method = RequestMethod.GET)
    public String carsList(Model model) {

        model.addAttribute("cars", cars);

        return "carsList";
    }

    // Cette méthode affiche la liste des modèles de voitures
    @RequestMapping(value = { "/modelsList" }, method = RequestMethod.GET)
    public String modelsList(Model model) {

            model.addAttribute("cars", cars);

        return "modelsList";
    }


    // Cette méthode affiche une voiture en cliquant sur son id
    @RequestMapping(value = { "/oneCar" }, method = RequestMethod.GET)
    public String oneCar(@RequestParam("id") int id, Model model) {

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getId() == id) {
                model.addAttribute("car", car);
                return "oneCar";
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "carsList";
    }

    // Cette méthode permet d'afficher le formulaire de création d'une voiture
    @RequestMapping(value = { "/addCar" }, method = RequestMethod.GET)
    public String showAddCarPage(Model model) {

        CarForm carForm = new CarForm();
        model.addAttribute("carForm", carForm);

        return "addCar";
    }

    // Cette méthode permet de créer une voiture
    @RequestMapping(value = { "/addCar" }, method = RequestMethod.POST)
    public String saveCar(Model model, //
                             @ModelAttribute("carForm") CarForm carForm) {

        Integer id = carForm.getId();
        String brand = carForm.getBrand();
        String type = carForm.getType();
        String color = carForm.getColor();
        String picture = carForm.getPicture();

        if (brand != null && brand.length() > 0 //
                && type != null && type.length() > 0 //
                && color != null && color.length() > 0) {
            Car newCar = new Car(id, brand, type, color, picture);
            cars.add(newCar);

            return "redirect:/carsList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCar";
    }

}

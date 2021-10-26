package com.cars.carsapp.controller;

import com.cars.carsapp.form.CarForm;
import com.cars.carsapp.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static List<Car> cars = new ArrayList<Car>();

    static {
        cars.add(new Car(1, "Ford", "Fiesta", "Blue"));
        cars.add(new Car(2, "Ford", "Kuga", "White"));
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

        int id = carForm.getId();
        String brand = carForm.getBrand();
        String type = carForm.getType();
        String color = carForm.getColor();

        if (brand != null && brand.length() > 0 //
                && type != null && type.length() > 0 //
                && color != null && color.length() > 0) {
            Car newCar = new Car(id, brand, type, color);
            cars.add(newCar);

            return "redirect:/carsList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCar";
    }

}

package com.cars.carsapp.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value="DifferentModel", description="Sample model for the documentation")
@Entity // classe scannée et prise en compte
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // identifié en tant que clé unique auto-générée
    private int id;

    private String brand;
    private String type;
    private String color;
    private String picture;

    public Car(){
    }

    public Car(int id, String brand, String type, String color, String picture) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}

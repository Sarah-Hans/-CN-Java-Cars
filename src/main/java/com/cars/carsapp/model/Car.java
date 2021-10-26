package com.cars.carsapp.model;

public class Car {

    private int id;
    private String brand;
    private String type;
    private String color;

    public Car(){
    }

    public Car(int id, String brand, String type, String color) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.color = color;
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
}

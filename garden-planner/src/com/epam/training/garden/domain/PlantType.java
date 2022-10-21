package com.epam.training.garden.domain;

public class PlantType {
  String name;
  double area;
  double waterAmount;

  public PlantType(String name, double area, double waterAmount) {
    this.name = name;
    this.area = area;
    this.waterAmount = waterAmount;
  }

  public String getName() {
    return name;
  }

  public double getArea() {
    return area;
  }

  public double getWaterAmount() {
    return waterAmount;
  }
}

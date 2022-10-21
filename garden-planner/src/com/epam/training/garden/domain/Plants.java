package com.epam.training.garden.domain;

import java.util.Arrays;

public enum Plants {
  CORN ("Corn", 0.4, 10),
  PUMPKIN ("Pumpkin", 2, 5),
  GRAPE ("Grape", 3, 5),
  TOMATO ("Tomato", 0.3, 10),
  CUCUMBER ("Cucumber", 0.3, 10);


  private final String name;
  private final double area;
  private final double waterAmount;

  Plants(String name, double area, double waterAmount) {
    this.name = name;
    this.area = area;
    this.waterAmount = waterAmount;
  }

  public void printPlantNames(){
    for (Plants plant:Plants.values()) {
      System.out.println("- " + plant.name);
    }
  }

  public Plants init (String name) throws IncorrectPlantNameException {
    return Arrays.stream(Plants.values())
        .filter(i -> i.name.equals(name))
        .findAny()
        .orElseThrow(() -> new IncorrectPlantNameException("Not valid plant name."));
  }

  public class IncorrectPlantNameException extends Exception {
    public IncorrectPlantNameException(String errorMessage) {
      super(errorMessage);
    }
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

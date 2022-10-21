package com.epam.training.garden.service;

import com.epam.training.garden.domain.GardenProperties;
import com.epam.training.garden.domain.PlantType;
import com.epam.training.garden.domain.Plants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GardenService{

  GardenProperties gardenProperties;

  public GardenService() {
  }

  public void printPlantNames(){
      for (Plants plant : Plants.values()) {
        System.out.println("- " + plant.name().toLowerCase(Locale.ROOT));
      }
  }

  public List<PlantType> getPlantTypes(){
    List<PlantType> plantTypeList = new ArrayList<>();

    PlantType corn = new PlantType("Corn", 0.4, 10);
    PlantType pumpkin = new PlantType("Pumpkin", 2, 5);
    PlantType grape = new PlantType("Grape", 3, 5);
    PlantType tomato = new PlantType("Tomato", 0.3, 10);
    PlantType cucumber = new PlantType("Cucumber", 0.4, 10);

    plantTypeList.add(corn);
    plantTypeList.add(pumpkin);
    plantTypeList.add(grape);
    plantTypeList.add(tomato);
    plantTypeList.add(cucumber);

    return plantTypeList;
  }

  public void setGardenProperties(GardenProperties gardenProperties) {
    this.gardenProperties = gardenProperties;
  }

  public Result evaluatePlan(Map<String, Integer> inputPlant){
    double requiredArea = 0.0;
    double waterSupply = 0.0;
    boolean areaOk = true;
    boolean waterOk = true;


    for (Map.Entry<String,Integer> set : inputPlant.entrySet()) {
      try{
        Plants plant = Plants.valueOf(set.getKey());
        Integer quantity = set.getValue();

        double area  = plant.getArea();
        requiredArea += area * quantity;

        double waterAmount = plant.getWaterAmount();
        waterSupply += waterAmount * quantity;
      } catch(IllegalArgumentException iae){
        System.out.println("Unknown plant: " + (set.getKey()).toLowerCase(Locale.ROOT));
      }
    }
    if (gardenProperties.getArea() < requiredArea){
      areaOk = false;
    }
    if (gardenProperties.getWaterSupply() < waterSupply){
      waterOk = false;
    }
    return new Result(requiredArea, waterSupply, areaOk, waterOk);
  }


}

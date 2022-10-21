package com.epam.training.garden;

import com.epam.training.garden.domain.GardenProperties;
import com.epam.training.garden.domain.Plants;
import com.epam.training.garden.service.GardenService;
import com.epam.training.garden.service.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

  public static void userInputGardenProperties(GardenService gardenService){
    Scanner sc = new Scanner(System.in);
    System.out.println("\n" + "Please enter the plants you would like to put in your garden. Press enter when you are done.");
    System.out.println("Please enter your garden properties.");
    System.out.print("Size (square meter): ");
    double area = Double.parseDouble(sc.next());
    System.out.print("Water supply (in liter): ");
    double water = Double.parseDouble(sc.next());
    GardenProperties gardenProperties = new GardenProperties(area,water);
    gardenService.setGardenProperties(gardenProperties);
    System.out.println("\nKnown plant types: ");
    gardenService.printPlantNames();

  }

  public static void userInputPlants(Map<String, Integer> inputPlant){
    Scanner sc = new Scanner(System.in);
    while (true){
      System.out.print("Enter plant (format: name,amount): ");
      String line = sc.nextLine().toUpperCase();
      String[] strParts = line.split(",");
      for (int i = 0; i < strParts.length; i++) {
        if((i + 1) % 2 == 0){
          String name = strParts[i - 1];
          Integer amount = Integer.parseInt(strParts[i]);
          inputPlant.put(name, amount);
        }
      }
      if(line.length() == 0){
        break;
      }
    }
  }

  public static void userInputResult(GardenService gardenService, Map<String, Integer> inputPlant){
    System.out.println("\n" + "***Result***");
    Result result = gardenService.evaluatePlan(inputPlant);
    System.out.println("\nRequired area: " + result.getArea() + " m2" +
        "\nWater need: " + result.getWaterAmount() + " l");
    if(result.isAreaOk() && result.isWaterOk()){
      System.out.println("Plan is feasible in your garden! :)");
    } else {
      System.out.println("Plan is NOT feasible in your garden! :(");
      if (result.isAreaOk()){
        System.out.println("- Not enough water.");
      } else if (result.isWaterOk()){
        System.out.println("- Not enough area.");
      } else {
        System.out.println("- Not enough area.\n" +
            "- Not enough water.");
      }
    }
  }

  public static void main(String[] args) {
    GardenService gardenService = new GardenService();
    System.out.println("***Welcome to Garden Planner***" + "\n");
    try {
      userInputGardenProperties(gardenService);
      Map<String, Integer> inputPlant = new HashMap<>();
      userInputPlants(inputPlant);
      userInputResult(gardenService, inputPlant);
    } catch (NumberFormatException nfe){
      System.out.println("\nIllegal input format.");
    }
  }
}

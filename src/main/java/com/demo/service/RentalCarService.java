package com.demo.service;

import com.demo.model.Car;
import com.demo.model.CarCollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


public class RentalCarService {

	//using restTemplate for request Response Modeling
    @Autowired
    public RestTemplate restTemplate;

    private String baseUri = "http://localhost:8089/demo/v1";
    private static final String BLUE_COLOR = "Blue";
	private static final String TESLA = "Tesla";
	
	/**
	 * Question 1: Print all the blue Teslas received in the web service response. Also print the notes
	 * @return List<Car>
	 */
    public List<Car> getCarsOfSpecificMakeColor(){
    	CarCollection carCollection = restTemplate.getForObject(baseUri + "/allCars", CarCollection.class);
        List<Car> blueTeslaCars = new ArrayList<Car>();
        for(Car car: carCollection.getCollections()){
        	//check the make and color, print if it is Blue Tesla
        	if(car.getMake() != null && car.getMake().equals(TESLA) && car.getCarMetadata() != null && car.getCarMetadata().getColor().equals(BLUE_COLOR)){
        		System.out.println(" Car Vin: "+car.getVin()+" Make: "+car.getVin()+" Model: "+car.getVin()+" Color: ");
        		System.out.println(" Notes: "+car.getCarMetadata().getNotes());
        		blueTeslaCars.add(car);
         	}
  		}
        return blueTeslaCars;
     }	
	
	/**
	 * Question 2.i: Method to return all the cars which have the lowest per day rental cost, per Price only basis
	 * @return List<Car>
	 */
    public List<Car> getCarsWithLowestPrice(){
		List<Car> allCars = getAllCarsServiceCall();
        List<Car> lowestPriceCars = new ArrayList<Car>();
		if(allCars == null || allCars.isEmpty()) {
			return lowestPriceCars;
		}
		//find the lowest price first
		Float lowestPrice = allCars.get(0).getCarPerDayrent().getPrice();
		for(Car car :allCars){
			if(Float.compare(car.getCarPerDayrent().getPrice(), lowestPrice) < 0){
				 lowestPrice = car.getCarPerDayrent().getPrice();
			}
		}
		//get all the cars for the above lowest price
		for(Car car :allCars){
			if(Float.compare(car.getCarPerDayrent().getPrice(), lowestPrice) == 0){
				lowestPriceCars.add(car);
			}
		}
		return lowestPriceCars;
	}
	
	/**
	 * Question 2.i: Method to return all cars which have the lowest per day rental cost, per discounted Price basis
	 * @return List<Car>
	 */
	public List<Car> getCarsWithLowestPriceAfterDiscount(){
		List<Car> allCars = getAllCarsServiceCall();
		List<Car> lowestPriceCarsWithDiscount = new ArrayList<Car>();
		if(allCars == null || allCars.isEmpty()) {
			return lowestPriceCarsWithDiscount;
		}
		//find the lowest discount price, first
		Float lowestPrice = allCars.get(0).getCarPerDayrent().getPrice()*(1 - allCars.get(0).getCarPerDayrent().getDiscount());
		for(Car car : allCars){
			 if(car.getCarPerDayrent().getPrice()*(1 - car.getCarPerDayrent().getDiscount()) < lowestPrice){
				 lowestPrice = car.getCarPerDayrent().getPrice()*(1 - car.getCarPerDayrent().getDiscount());
			 }
		 }
		//get all the cars matching the lowest discounted price
		 for(Car car :allCars){
			 if(car.getCarPerDayrent().getPrice()*(1 - car.getCarPerDayrent().getDiscount()) == lowestPrice){
				 lowestPriceCarsWithDiscount.add(car);
			 }
		 }
		return lowestPriceCarsWithDiscount;
	}
	
	/**
	 * Question 3: Method to return highest revenue generating cars
	 * @return Car - Car 
	 */
	public Car getHighestRevenueGeneratingCar(){
		List<Car> allCars = getAllCarsServiceCall();
		Float profit = 0.0f;
		Car highestRevenueCar = null;
		for(Car c : allCars){
			Float currProfit = calculateCarProfit(c);
			if(profit < currProfit) {
				profit = currProfit;
				highestRevenueCar = c;
			}
		 }
		return highestRevenueCar;
	}
	
	/**
	 * Helper method calculates the profit on a car 
	 * @param car
	 * @return Float - profit
	 */
	private Float calculateCarProfit(Car car) {
		Float expense = car.getCarMetrics().getYoymaintenancecost() + car.getCarMetrics().getDepreciation();
		Float earnings = car.getCarMetrics().getCarRentalCount().getYearToDate()*car.getCarPerDayrent().getPrice();
		return earnings - expense;
	}
	
	/**
	 * helper method makes service call to get all the cars
	 * @return List<Car>
	 */
	private List<Car> getAllCarsServiceCall() {
		CarCollection carCollection = restTemplate.getForObject(baseUri + "/allCars", CarCollection.class);
		List<Car> allCars = carCollection.getCollections();
		return allCars;
	}

}

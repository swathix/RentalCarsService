package com.demo.model;

public class Metrics {
	
	
	private Float yoymaintenancecost;
		
	private Float depreciation;
	
	private RentalCount carRentalCount;
	
	public Metrics() {
		
	}
	public Metrics(Float yoymaintenancecost, Float depreciation, RentalCount carRentalCount ){
		this.setYoymaintenancecost(yoymaintenancecost);
		this.depreciation=depreciation;
		this.carRentalCount=carRentalCount;
	}
	
	public void setDepreciation(Float depreciation){
		this.depreciation=depreciation;
	}
	
	public void setCarRentalCount(RentalCount carRentalCount) {
		this.carRentalCount = carRentalCount;
	}
	
	public Float getDepreciation(){
		return this.depreciation;
	}
	
	public RentalCount getCarRentalCount() {
		return this.carRentalCount;
	}
	
	public Float getYoymaintenancecost() {
		return yoymaintenancecost;
	}
	
	public void setYoymaintenancecost(Float yoymaintenancecost) {
		this.yoymaintenancecost = yoymaintenancecost;
	}

}

package com.demo.model;

//For sample JSON response I created POJO's respectively as needed 
public class Car {
	
	private String make;
	
	private String model;
	
	private String vin;

	private MetaData carMetadata;
	
    private PerDayRent carPerDayrent;
    
    private Metrics carMetrics;
    
    public Car(String make, String model, String vinNum, MetaData carMetadata, PerDayRent carPerDayrent, Metrics carMetrics){
    	this.make=make;
    	this.model=model;
    	this.vin = vinNum;
    	this.carMetadata=carMetadata;
    	this.carPerDayrent=carPerDayrent;
    	this.carMetrics=carMetrics;
    }
    public Car(){
    	
    }
    
	public String getMake(){
		return this.make;
	}

	public void setMake(String make){
		this.make=make;
	}
	
	public String getModel(){
		return this.model;
	}
	
	public void setModel(String model){
		this.model=model;
	}
	
	public String getVin() {
		return this.vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public MetaData getCarMetadata() {
		return this.carMetadata;
	}
	
	public void setCarMetadata(MetaData carMetadata) {
		this.carMetadata = carMetadata;
	}

	public void setCarPerDayrent(PerDayRent carPerDayrent) {
		this.carPerDayrent = carPerDayrent;
	}
	
	public PerDayRent getCarPerDayrent() {
		return this.carPerDayrent;
	}
	
	public Metrics getCarMetrics() {
		return this.carMetrics;
	}

	public void setCarMetrics(Metrics carMetrics) {
		this.carMetrics = carMetrics;
	}

}

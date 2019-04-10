package com.demo.model;

public class PerDayRent {
	
	private  Float Price;
		
	private Float Discount;
	
	public PerDayRent() {
		
	}
	public PerDayRent(Float Price, Float Discount){
		this.Price=Price;
		this.Discount=Discount;
	}
	
	public void setPrice(Float Price){
		this.Price=Price;
	}
	
	public void setDiscount(Float Discount){
		this.Discount=Discount;
	}
	
	public Float getPrice(){
		return this.Price;
	}
	
	public Float getDiscount(){
		return this.Discount;
	}	

}

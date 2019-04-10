package com.demo.model;

public class RentalCount {
	
	
	private Integer lastweek;
		
	private Integer yeartodate;
	
	public RentalCount() {
		
	}
	
	public RentalCount(Integer lastweek, Integer yeartodate)
	{
		this.lastweek=lastweek;
		this.yeartodate=yeartodate;
	}
	
	public void setLastWeek(Integer lastweek){
		this.lastweek=lastweek;
	}
	
	public void setYearToDate(Integer yeartodate){
		this.yeartodate=yeartodate;
	}
	
	public Integer getLastWeek(){
		return this.lastweek;
	}
	
	
	public Integer getYearToDate(){
		return this.yeartodate;
	}

}

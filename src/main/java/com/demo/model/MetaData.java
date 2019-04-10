package com.demo.model;

public class MetaData {
	
	private String Color;
	
	private String Notes;
	
	public MetaData(String Color, String Notes){
		this.Color=Color;
		this.Notes=Notes;
	}
	public MetaData(){
		
	}
	public void setColor(String Color){
		this.Color=Color;
	}
	
	public void setNotes(String Notes){
		this.Notes=Notes;
	}
	
	public String getColor(){
		return this.Color;
	}
	
	public String getNotes(){
		return this.Notes;
	}

}

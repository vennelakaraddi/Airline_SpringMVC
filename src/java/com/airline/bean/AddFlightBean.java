package com.airline.bean;  
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AddFlight")
public class AddFlightBean {
	

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  

	@Size(min=1,message="To location required") 
	private String tolocation;  
	@Size(min=1,message="From location required") 
	private String fromlocation;
	 
	private int totalseats;

	@Size(min=1,message="Airline name required") 
	private String airlinename;
	
	private double price;  
	

  
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
public String getTolocation() {  
    return tolocation;  
}  
public void setTolocation(String tolocation) {  
    this.tolocation = tolocation;  
}  

public String getFromlocation() {  
    return fromlocation;  
}  
public void setFromlocation(String fromlocation) {  
    this.fromlocation = fromlocation;  
}  
public int getTotalseats() {  
    return totalseats;  
}  
public void setTotalseats(int totalseats) {  
    this.totalseats = totalseats;  
}  

public String getAirlinename() {  
    return airlinename;  
}  
public void setAirlinename(String airlinename) {  
    this.airlinename = airlinename;  
} 
  
public double getPrice() {  
    return price;  
}  
public void setPrice(double price) {  
    this.price = price;  
} 
}  
package com.airline.bean;  
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BookFlight")
public class BookFlightBean {
	

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  

	@Size(min=1,message="To location required") 
	private String tolocation;  
	@Size(min=1,message="From location required") 
	private String fromlocation;
	 
	private int totaltickets;

	@Size(min=1,message="Airline name required") 
	private String airlinename;
	
	private String customername;  

	private Date flightdate;
	

  
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
public int getTotaltickets() {  
    return totaltickets;  
}  
public void setTotaltickets(int totaltickets) {  
    this.totaltickets = totaltickets;  
}  

public String getAirlinename() {  
    return airlinename;  
}  
public void setAirlinename(String airlinename) {  
    this.airlinename = airlinename;  
} 
  
public String getCustomername() {  
    return customername;  
}  
public void setCustomername(String customername) {  
    this.customername = customername;  
}

public Date getFlightdate() {  
    return flightdate;  
}  
public void setFlightdate(Date flightdate) {  
    this.flightdate = flightdate;  
} 
}  
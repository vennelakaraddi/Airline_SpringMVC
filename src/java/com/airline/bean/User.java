package com.airline.bean;  
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	

	@Id
	@Column(name="customerid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerid;  

	@Size(min=1,message="Username must be enter") 
	private String username;  
	@Size(min=1,message="Password must be enter") 
	private String password;
	 
	@Size(min=1,message="Confirm password must be enter") 
	private String confirmpassword;
	
	  
public int getCustomerid() {  
    return customerid;  
}  
public void setCustomerid(int customerid) {  
    this.customerid = customerid;  
}  
public String getUsername() {  
    return username;  
}  
public void setUsername(String username) {  
    this.username = username;  
}  

public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  

public String getConfirmpassword() {  
    return confirmpassword;  
}  
public void setConfirmpassword(String confirmpassword) {  
    this.confirmpassword = confirmpassword;  
} 
  

}  
package com.airline.bean;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public class AdminLoginBean{
	
	@Size(min=1,message="username required")
	private String username;
	@Size(min=1,message="password required")
	private String password;

public void setUsername(String username) {
	this.username = username;
}
public String getUsername() {
	return username;
}

public void setPassword(String password) {
	this.password = password;
}
public String getPassword() {
	return password;
}
}
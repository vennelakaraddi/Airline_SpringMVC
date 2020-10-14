package com.airline.dao; 
import java.util.List;
import com.airline.bean.AddFlightBean;
import com.airline.bean.User;
import com.airline.bean.BookFlightBean;

public interface AirlineDAO {

	public void addFlight(AddFlightBean afb);
	public List<AddFlightBean> listFlight();
	public void editFlight(AddFlightBean afb);
	public void deleteFlight(int fid);

	public void addUser(User u);
	public List<User> listUser();

	public void bookFlight(BookFlightBean u);
	public List<BookFlightBean> listBooking();

}
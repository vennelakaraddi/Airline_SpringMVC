package com.airline.service; 
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airline.dao.AirlineDAO;
import com.airline.bean.AddFlightBean;
import com.airline.bean.User;
import com.airline.bean.BookFlightBean;


@Service
public class AirlineServiceImpl implements AirlineService {
	
	private AirlineDAO airlineDAO;

public void setAirlineDAO(AirlineDAO airlineDAO) {
	this.airlineDAO = airlineDAO;
}

@Override
@Transactional
public List<BookFlightBean> listBooking() {
	return this.airlineDAO.listBooking();
}

@Override
@Transactional
public void bookFlight(BookFlightBean bfb) {
	this.airlineDAO.bookFlight(bfb);
}

@Override
@Transactional
public List<User> listUser() {
	return this.airlineDAO.listUser();
}

@Override
@Transactional
public void addUser(User user) {
	this.airlineDAO.addUser(user);
}

@Override
@Transactional
public void addFlight(AddFlightBean afb) {
	this.airlineDAO.addFlight(afb);
}

@Override
@Transactional
public void editFlight(AddFlightBean afb) {
	this.airlineDAO.editFlight(afb);
}

@Override
@Transactional
public List<AddFlightBean> listFlight() {
	return this.airlineDAO.listFlight();
}

@Override
@Transactional
public void deleteFlight(int id) {
	this.airlineDAO.deleteFlight(id);
}

}
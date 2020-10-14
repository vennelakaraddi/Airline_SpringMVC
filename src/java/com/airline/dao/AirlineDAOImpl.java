package com.airline.dao; 
import java.util.List;
import com.airline.bean.AddFlightBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.airline.bean.User;
import com.airline.bean.BookFlightBean;

@Repository
public class AirlineDAOImpl implements AirlineDAO {

	private SessionFactory sessionFactory;
	
public void setSessionFactory(SessionFactory sf) {
	this.sessionFactory = sf;
}

@SuppressWarnings("unchecked")
@Override
public List<BookFlightBean> listBooking() {
	Session session = this.sessionFactory.getCurrentSession();
	List<BookFlightBean> booking = session.createQuery("from BookFlightBean").list();
	return booking;
}

@Override
public void bookFlight(BookFlightBean bfb) {
	Session session = this.sessionFactory.getCurrentSession();
	session.persist(bfb);
	System.out.println("booking details added : "+bfb);
}

@SuppressWarnings("unchecked")
@Override
public List<User> listUser() {
	Session session = this.sessionFactory.getCurrentSession();
	List<User> user = session.createQuery("from User").list();
	return user;
}

@Override
public void addUser(User user) {
	Session session = this.sessionFactory.getCurrentSession();
	session.persist(user);
	System.out.println("New user detail added : "+user);
}

@Override
public void addFlight(AddFlightBean afb) {
	Session session = this.sessionFactory.getCurrentSession();
	session.persist(afb);
	System.out.println("Flight detail added : "+afb);
}

@Override
public void editFlight(AddFlightBean afb) {
	Session session = this.sessionFactory.getCurrentSession();
	session.update(afb);
	System.out.println("Flight details updated successfully, Details = "+afb.getId());
}

@SuppressWarnings("unchecked")
@Override
public List<AddFlightBean> listFlight() {
	Session session = this.sessionFactory.getCurrentSession();
	List<AddFlightBean> afb = session.createQuery("from AddFlightBean").list();
	return afb;
}

@Override
public void deleteFlight(int id) {
	Session session = this.sessionFactory.getCurrentSession();
	AddFlightBean afb = (AddFlightBean) session.load(AddFlightBean.class, new Integer(id));
	if(null != afb){
		session.delete(afb);
	}
	System.out.println("Flight details deleted successfully, flight details = "+afb);
}

}
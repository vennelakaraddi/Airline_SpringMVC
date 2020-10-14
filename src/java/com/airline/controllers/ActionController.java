package com.airline.controllers;  
import org.springframework.validation.BindingResult;
import java.util.ArrayList;  
import java.util.List;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
import com.airline.bean.AdminLoginBean; 
import org.springframework.ui.Model;  
import javax.validation.Valid;
import com.airline.bean.*;
import com.airline.service.*;
import com.airline.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.Map;

@Controller  
@SessionAttributes({"username","cart"})
public class ActionController {  

	private AirlineService airlineService;

/*@RequestMapping("/BookFlight") 
public String bookFlight(Model m) {
	Map<String,Object> map = m.asMap();
	for(Map.Entry<String,Object> me : map.entrySet()){
		System.out.println(me.getKey()+"****================"+me.getValue());
	}
	return "CustomerScreen";
}*/

@RequestMapping("/BookFlight") 
public ModelAndView bookFlight(Model m){
	BookFlightBean bfb = new BookFlightBean();
	m.addAttribute("BookFlightBean", bfb);
	return new ModelAndView("BookFlight","command",bfb);  
}
@RequestMapping(value="/FlightSearchAction",method = RequestMethod.POST) 
public ModelAndView flightSearchAction(@Valid @ModelAttribute("BookFlightBean") BookFlightBean bfb, BindingResult br,Model m) {
	List<BookFlightBean> list = new ArrayList<BookFlightBean>();
	if(br.hasErrors()) {  
		return new ModelAndView("BookFlight","command",bfb);  
	}  else {
		String customer = "none";
		Map<String,Object> map = m.asMap();
		for(Map.Entry<String,Object> me : map.entrySet()){
			if(me.getKey().equals("username")) {
				customer = me.getValue().toString();
				break;
			}
		}
		System.out.println(bfb.getFlightdate()+"===="+customer);
		List<AddFlightBean> flight_list = this.airlineService.listFlight();
		for(int i=0;i<flight_list.size();i++) {
			AddFlightBean afb = flight_list.get(i);
			String to = afb.getTolocation();
			String from = afb.getFromlocation();
			int seats = afb.getTotalseats();
			String airline = afb.getAirlinename();
			boolean available = getAvailable(to,from,seats,airline,bfb.getFlightdate().toString());
			if(available && bfb.getTolocation().equals(to) && bfb.getFromlocation().equals(from)) {
				BookFlightBean temp = new BookFlightBean();
				temp.setTolocation(to);
				temp.setFromlocation(from);
				temp.setAirlinename(airline);
				temp.setFlightdate(bfb.getFlightdate());
				temp.setCustomername(customer);
				temp.setTotaltickets(bfb.getTotaltickets());
				list.add(temp);
			}
		}
		if(list.size() > 0)
			m.addAttribute("cart",list);
		return new ModelAndView("SearchList","list",list);  
	}
}  
public boolean getAvailable(String to,String from,int seats,String airline,String date) {
	List<BookFlightBean> list = this.airlineService.listBooking();
	int count = 0;
	for(int i=0;i<list.size();i++){
		BookFlightBean temp = list.get(i);
		if(temp.getTolocation().equals(to) && temp.getFromlocation().equals(from) && temp.getFlightdate().toString().equals(date)){
			count = count + temp.getTotaltickets();
		}
	}
	boolean flag = false;
	if(count < seats)
		flag = true;
	return flag;
}

@RequestMapping("/BookingConfirm") 
public ModelAndView bookingConfirm(@RequestParam("t1") String to,@RequestParam("t2") String from,  @RequestParam("t3") String seats,@RequestParam("t4") String date,@RequestParam("t5") String airline, Model model) {
	String customer = "none";
	Map<String,Object> map = model.asMap();
	for(Map.Entry<String,Object> me : map.entrySet()){
		if(me.getKey().equals("username")) {
			customer = me.getValue().toString();
			break;
		}
	}
	BookFlightBean bfb = new BookFlightBean();
	bfb.setTolocation(to);
	bfb.setFromlocation(from);
	bfb.setTotaltickets(Integer.parseInt(seats));
	bfb.setAirlinename(airline);
	bfb.setCustomername(customer);
	bfb.setFlightdate(java.sql.Date.valueOf(date));
	this.airlineService.bookFlight(bfb);
	model.addAttribute("msg", "Tickets booking confirmed");
	Map<String,Object> maps = model.asMap();
	for(Map.Entry<String,Object> me : maps.entrySet()){
		if(me.getKey().equals("cart")) {
			List<BookFlightBean> list = (List<BookFlightBean>)me.getValue();
			list.clear();
			break;
		}
	}
	maps.remove("cart");
    //return new ModelAndView("CustomerScreen");//will redirect to viewemp request mapping 
	ModelAndView modelAndView = new ModelAndView("pdfView", "command",bfb);
    return modelAndView;
}


@RequestMapping("/CustomerLogin") 
public ModelAndView customerLogin(Model m){
	User user = new User();
	m.addAttribute("User", user);
	return new ModelAndView("CustomerLogin","command",user);  
}

@RequestMapping(value="/CustomerLoginAction",method = RequestMethod.POST)
public ModelAndView customerLoginAction(@Valid @ModelAttribute("User") User user, BindingResult br, Model m){
	System.out.println(br.hasErrors()+" "+user.getUsername()+"============== "+user.getPassword());
	if(br.hasErrors()) {
		return new ModelAndView("CustomerLogin","command",user);
	}  else {
		String page = "none";
		List<User> list = this.airlineService.listUser();
		boolean flag = false;
		ModelAndView mv = new ModelAndView();
		for(int i=0;i<list.size();i++){
			User usr = list.get(i);
			if(usr.getUsername().equals(user.getUsername()) && usr.getPassword().equals(user.getPassword())){
				flag = true;
				break;
			}
		}
		if (flag) {
			m.addAttribute("username",user.getUsername());
			page = "redirect:/CustomerScreen"; 
		} else {
			page = "CustomerLogin";
			m.addAttribute("msg", "Invalid Login");
        }
		mv.setViewName(page);
		return mv;
    }  

}



@RequestMapping("/Register") 
public ModelAndView register(Model m){
	User user = new User();
	m.addAttribute("User", user);
	return new ModelAndView("Register","command",user);  
}

@RequestMapping(value="/RegisterAction",method = RequestMethod.POST) 
public ModelAndView registerUser(@Valid @ModelAttribute("User") User user, BindingResult br,Model m) {
	System.out.println(user.getUsername());
	if(br.hasErrors()) {  
		return new ModelAndView("Register","command",user);  
	}  
	if(!user.getPassword().equals(user.getConfirmpassword())) {
		m.addAttribute("msg", "Password & Confirm password must be equal");
        return new ModelAndView("Register");
	} else {
		this.airlineService.addUser(user);
		m.addAttribute("msg", "Signup process completed");
        return new ModelAndView("Register");
	}
}  


@Autowired(required=true)
@Qualifier(value="airlineService")
public void setAirlineService(AirlineService as){
	this.airlineService = as;
}

public int getSeats(String to,String from,String airline){
	int seats = 0;
	List<AddFlightBean> flight_list = this.airlineService.listFlight();
	for(int i=0;i<flight_list.size();i++) {
		AddFlightBean afb = flight_list.get(i);
		if(afb.getTolocation().equals(to) && afb.getFromlocation().equals(from) && afb.getAirlinename().equals(airline)){
			seats = afb.getTotalseats();
			break;
		}
	}
	return seats;
}


@RequestMapping("/CustomerScreen") 
public ModelAndView customerScreen(Model m) {
	List<BookFlightBean> list = null;
	String customer = "none";
	Map<String,Object> map = m.asMap();
	for(Map.Entry<String,Object> me : map.entrySet()){
		if(me.getKey().equals("cart")) {
			list = (List<BookFlightBean>)me.getValue();
			break;
		}
	}
	for(Map.Entry<String,Object> me : map.entrySet()){
		if(me.getKey().equals("username")) {
			customer = me.getValue().toString();
			break;
		}
	}
	List<BookFlightBean> newlist = new ArrayList<BookFlightBean>();
	if(list != null){
		for(int i=0;i<list.size();i++){
			BookFlightBean bfb = list.get(i);
			int seats = getSeats(bfb.getTolocation(),bfb.getFromlocation(),bfb.getAirlinename());
			boolean available = getAvailable(bfb.getTolocation(),bfb.getFromlocation(),seats,bfb.getAirlinename(),bfb.getFlightdate().toString());
			if(available && bfb.getCustomername().equals(customer)){
				newlist.add(bfb);
			}
		}
	}
	return new ModelAndView("CustomerScreen","list",newlist); 
}

@RequestMapping("/AdminScreen") 
public String adminScreen(Model m) {
	return "AdminScreen";
}
@RequestMapping("/Logout") 
public String logout(Model m) {
	System.out.println(m.containsAttribute("username")+" check session "+m.containsAttribute("cart"));
	Map<String,Object> map = m.asMap();
	map.remove("username");
	System.out.println(m.containsAttribute("username")+" check session "+m.containsAttribute("cart"));
	return "index";
}

@RequestMapping("/DeleteFlightData") 
public ModelAndView deleteFlightData(@RequestParam("t1") String id, Model model) {
	this.airlineService.deleteFlight(Integer.parseInt(id.trim()));
	AddFlightBean afb = new AddFlightBean();
	model.addAttribute("DeleteFlight", afb);
	model.addAttribute("msg", "Flight details deleted");
    return new ModelAndView("DeleteFlight"); 
}

@RequestMapping("/EditFlightData") 
public ModelAndView editFlightData(@RequestParam("t1") String id,@RequestParam("t2") String to,  @RequestParam("t3") String from,@RequestParam("t4") String seats,@RequestParam("t5") String airline,@RequestParam("t6") String price, Model model) {
	System.out.println(to+"==="+from);
	model.addAttribute("id",id);
	model.addAttribute("to",to);
	model.addAttribute("from",from);
	model.addAttribute("seats",seats);
	model.addAttribute("airline",airline);
	model.addAttribute("price",price);
	AddFlightBean afb = new AddFlightBean();
	afb.setId(Integer.parseInt(id));
	afb.setTolocation(to);
	afb.setFromlocation(from);
	afb.setTotalseats(Integer.parseInt(seats));
	afb.setAirlinename(airline);
	afb.setPrice(Double.parseDouble(price));
	model.addAttribute("EditFlight", afb);
	return new ModelAndView("EditFlightData"); 
}

@RequestMapping(value="/EditFlightAction",method = RequestMethod.POST) 
public ModelAndView editFlightAction(@Valid @ModelAttribute("EditFlight") AddFlightBean afb, BindingResult br,Model m) {
	System.out.println(afb.getAirlinename()+"===="+afb.getId());
	if(br.hasErrors()) {  
		return new ModelAndView("EditFlightData","command",afb);  
	}  else {
		this.airlineService.editFlight(afb);
		m.addAttribute("msg", "Flight details edited");
        return new ModelAndView("EditFlightData");//will redirect to viewemp request mapping 
	}
}  

@RequestMapping("/AddFlight") 
public ModelAndView addFlight(Model m) {
	AddFlightBean afb = new AddFlightBean();
	m.addAttribute("AddFlight", afb);
	return new ModelAndView("AddFlight","command",afb);
}

@RequestMapping(value="/AddFlightAction",method = RequestMethod.POST) 
public ModelAndView save(@Valid @ModelAttribute("AddFlight") AddFlightBean afb, BindingResult br,Model m) {
	System.out.println(afb.getAirlinename());
	if(br.hasErrors()) {  
		return new ModelAndView("AddFlight","command",afb);  
	}  else {
		this.airlineService.addFlight(afb);
		m.addAttribute("msg", "Flight details added");
        return new ModelAndView("redirect:/AddFlight");//will redirect to viewemp request mapping 
	}
}  

@RequestMapping("/AdminLogin") 
public ModelAndView adminLoginForm(Model m){
	AdminLoginBean alb = new AdminLoginBean();
	m.addAttribute("AdminLogin", alb);
	return new ModelAndView("AdminLogin","command",alb);  
}

@RequestMapping(value="/AdminLoginAction",method = RequestMethod.POST)
public ModelAndView save(@Valid @ModelAttribute("AdminLogin") AdminLoginBean alb, BindingResult br, Model m){
	System.out.println(br.hasErrors()+" "+alb.getUsername()+"============== "+alb.getPassword());
	if(br.hasErrors()) {
		return new ModelAndView("AdminLogin","command",alb);
	}  else {
		String page = "none";
		if (alb.getUsername().equals("admin") && alb.getPassword().equals("admin")) {
			page = "redirect:/AdminScreen"; 
		} else {
			page = "AdminLogin";
			m.addAttribute("error", "Invalid Login");
        }
		return new ModelAndView(page);
    }  

}

@RequestMapping("/EditFlight")  
public ModelAndView editFlight() {
	List<AddFlightBean> list = this.airlineService.listFlight(); 
	return new ModelAndView("EditFlight","list",list);  
}  
@RequestMapping("/DeleteFlight")  
public ModelAndView deleteFlight() {
	List<AddFlightBean> list = this.airlineService.listFlight(); 
	return new ModelAndView("DeleteFlight","list",list);  
}  
}  
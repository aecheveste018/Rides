package businessLogic;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Credentials;
import domain.Driver;
import domain.Passenger;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}
	
	
	
	
	

	public void addUser(String email, String name, Character tipo) {
		dbManager.open();
		dbManager.addUser(email, name, tipo);
		dbManager.close();
	}
	
	public String findPassword(String email) {
		dbManager.open();
		String resp = "";
		List<Credentials> list = dbManager.getCredentials();
		
		  Iterator<Credentials> it = list.iterator();
		  while(it.hasNext()) {
			  Credentials current = it.next();
			  System.out.println(current);
			  System.out.println("test");
			  if(email.equals(current.getmail())) {
				  resp = current.getPassword();
			  }
		  }
		
		dbManager.close();
		
		return resp;
	}
	public void addCredential(String mail, String psw, Character tipe) {
		dbManager.open();
		dbManager.addCredential(mail, psw, tipe);
		dbManager.close();
	}
	
	public boolean exists(String email) {
		dbManager.open();
		List<Credentials> list = dbManager.getCredentials();
		System.out.println("Epa") ;
		  Iterator<Credentials> it = list.iterator();
		  while(it.hasNext()) {
			  Credentials current = it.next();
			  if(email.equals(current.getmail())) {
				  dbManager.close();
				 return true;
			  }
		  }
		
		
		dbManager.close();
		return false;
	}
	public String getTipo(String email) {
		Object tipo=0;
		dbManager.open();
		List<Credentials> list = dbManager.getCredentials();
		System.out.println("Epa") ;
		  Iterator<Credentials> it = list.iterator();
		  while(it.hasNext()) {
			  Credentials current = it.next();
			  if(email.equals(current.getmail())) {
				  tipo= dbManager.getClass();
				  dbManager.close();
				 return (String)tipo;
			  }
		  }
		
		
		dbManager.close();
		return (String)tipo;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    @Override
    public boolean requestReservation(String passengerEmail, Ride ride) {
        dbManager.open();
        boolean success = dbManager.requestReservation(passengerEmail, ride);
        dbManager.close();
        return success;
    }
    @Override
    public Driver getDriverByEmail(String email) {
        dbManager.open();
    	Driver d=dbManager.getDriverByEmail(email);
    	dbManager.close();
    	return d;
    }
    
    @Override
    public Passenger getPassengerByEmail(String email) { 
    	dbManager.open();
    	Passenger p=dbManager.getPassengerByEmail(email);
    	dbManager.close();
    	return p;
    }
    @Override
	public List<Ride> getBookingForDriver(Driver driver) {
    	dbManager.open();
    	List<Ride> list=dbManager.getBookingsForDriver(driver);
    	dbManager.close();
    	return list;
    }

	
}


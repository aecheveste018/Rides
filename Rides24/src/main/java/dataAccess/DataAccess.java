package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//<<<<<<< HEAD
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Credentials;
import domain.Driver;
import domain.Passenger;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * It implements the data access to the objectDb database
 */
//public class DataAccess {
//	private EntityManager db;
//	private EntityManagerFactory emf;
//
//	ConfigXML c = ConfigXML.getInstance();
//
//	public DataAccess() {
//		if (c.isDatabaseInitialized()) {
//			String fileName = c.getDbFilename();
//
//			File fileToDelete = new File(fileName);
//			if (fileToDelete.delete()) {
//				File fileToDeleteTemp = new File(fileName + "$");
//				fileToDeleteTemp.delete();
//
//				System.out.println("File deleted");
//			} else {
//				System.out.println("Operation failed");
//			}
//		}
//		open();
//		if (c.isDatabaseInitialized())
//			initializeDB();
//
//		System.out.println("DataAccess created => isDatabaseLocal: " + c.isDatabaseLocal() + " isDatabaseInitialized: "
//				+ c.isDatabaseInitialized());
//
//		close();
//
//	}
//
//	public DataAccess(EntityManager db) {
//		this.db = db;
//	}
//
//	/**
//	 * This is the data access method that initializes the database with some events
//	 * and questions. This method is invoked by the business logic (constructor of
//	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
//	 * dataBaseOpenMode of resources/config.xml file
//	 */
//	public void initializeDB() {
//
//		db.getTransaction().begin();
//
//		try {
//
//			Calendar today = Calendar.getInstance();
//
//			int month = today.get(Calendar.MONTH);
//			int year = today.get(Calendar.YEAR);
//			if (month == 12) {
//				month = 1;
//				year += 1;
//			}
//
//			// Create drivers
//			Driver driver1 = new Driver("driver1@gmail.com", "Aitor Fernandez");
//			Driver driver2 = new Driver("driver2@gmail.com", "Ane Gaztañaga");
//			Driver driver3 = new Driver("driver3@gmail.com", "Test driver");
//
//			// Create rides
//			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 4, 7);
//			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year, month, 6), 4, 8);
//			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 4, 4);
//
//			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year, month, 7), 4, 8);
//
//			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 3, 3);
//			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 2, 5);
//			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year, month, 6), 2, 5);
//
//			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 14), 1, 3);
//
//			db.persist(driver1);
//			db.persist(driver2);
//			db.persist(driver3);
//
//			db.getTransaction().commit();
//			System.out.println("Db initialized");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * This method returns all the cities where rides depart
//	 * 
//	 * @return collection of cities
//	 */
//	public List<String> getDepartCities() {
//		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
//		List<String> cities = query.getResultList();
//		return cities;
//
//	}
//
//	// Para añadir usuarios TIPO: P-> Passenger | R-> rider
//	public void addUser(String email, String name, Character tipo) {
//		if (tipo.equals('P')) {
//			Passenger client = new Passenger(name, email);
//			db.getTransaction().begin();
//			db.persist(client);
//			db.getTransaction().commit();
//		} else if (tipo.equals('D')) {
//			Driver driver = new Driver(email, name);
//			db.getTransaction().begin();
//			db.persist(driver);
//			db.getTransaction().commit();
//		} else {
//			System.out.println("Error");
//		}
//
//	}
//
//	public void addCredential(String mail, String psw, Character tipe) {
//		Credentials cred = new Credentials(mail, psw, tipe);
//		db.getTransaction().begin();
//		db.persist(cred);
//		db.getTransaction().commit();
//
//	}
//
//	public List<Credentials> getCredentials() {
//
//		TypedQuery<Credentials> query = db.createQuery("SELECT DISTINCT r FROM Credentials r ORDER BY r ",
//				Credentials.class);
//		List<Credentials> cred = query.getResultList();
//
//		return cred;
//	}
//
//	/**
//	 * This method returns all the arrival destinations, from all rides that depart
//	 * from a given city
//	 * 
//	 * @param from the depart location of a ride
//	 * @return all the arrival destinations
//	 */
//	public List<String> getArrivalCities(String from) {
//		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",
//				String.class);
//		query.setParameter(1, from);
//		List<String> arrivingCities = query.getResultList();
//		return arrivingCities;
//
//	}
//
//	/**
//	 * This method creates a ride for a driver
//	 * 
//	 * @param from        the origin location of a ride
//	 * @param to          the destination location of a ride
//	 * @param date        the date of the ride
//	 * @param nPlaces     available seats
//	 * @param driverEmail to which ride is added
//	 * 
//	 * @return the created ride, or null, or an exception
//	 * @throws RideMustBeLaterThanTodayException if the ride date is before today
//	 * @throws RideAlreadyExistException         if the same ride already exists for
//	 *                                           the driver
//	 */
//	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail)
//			throws RideAlreadyExistException, RideMustBeLaterThanTodayException {
//		System.out.println(">> DataAccess: createRide=> from= " + from + " to= " + to + " driver=" + driverEmail
//				+ " date " + date);
//		try {
//			if (new Date().compareTo(date) > 0) {
//				throw new RideMustBeLaterThanTodayException(
//						ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
//			}
//			db.getTransaction().begin();
//
//			Driver driver = db.find(Driver.class, driverEmail);
//			if (driver.doesRideExists(from, to, date)) {
//				db.getTransaction().commit();
//				throw new RideAlreadyExistException(
//						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
//			}
//			Ride ride = driver.addRide(from, to, date, nPlaces, price);
//			// next instruction can be obviated
//			db.persist(driver);
//			insertRide(ride);
//			db.getTransaction().commit();
//
//			return ride;
//		} catch (NullPointerException e) {
//			// TODO Auto-generated catch block
//			db.getTransaction().commit();
//			return null;
//		}
//
//	}
//
//	/**
//	 * This method retrieves the rides from two locations on a given date
//	 * 
//	 * @param from the origin location of a ride
//	 * @param to   the destination location of a ride
//	 * @param date the date of the ride
//	 * @return collection of rides
//	 */
//	public List<Ride> getRides(String from, String to, Date date) {
//		System.out.println(">> DataAccess: getRides=> from= " + from + " to= " + to + " date " + date);
//
//		List<Ride> res = new ArrayList<>();
//		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",
//				Ride.class);
//		query.setParameter(1, from);
//		query.setParameter(2, to);
//		query.setParameter(3, date);
//		List<Ride> rides = query.getResultList();
//		for (Ride ride : rides) {
//			res.add(ride);
//		}
//		return res;
//	}
//
//	/**
//	 * This method retrieves from the database the dates a month for which there are
//	 * events
//	 * 
//	 * @param from the origin location of a ride
//	 * @param to   the destination location of a ride
//	 * @param date of the month for which days with rides want to be retrieved
//	 * @return collection of rides
//	 */
//	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
//		System.out.println(">> DataAccess: getEventsMonth");
//		List<Date> res = new ArrayList<>();
//
//		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
//		Date lastDayMonthDate = UtilDate.lastDayMonth(date);
//
//		TypedQuery<Date> query = db.createQuery(
//				"SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",
//				Date.class);
//
//		query.setParameter(1, from);
//		query.setParameter(2, to);
//		query.setParameter(3, firstDayMonthDate);
//		query.setParameter(4, lastDayMonthDate);
//		List<Date> dates = query.getResultList();
//		for (Date d : dates) {
//			res.add(d);
//		}
//		return res;
//	}
//
//	public void open() {
//
//		String fileName = c.getDbFilename();
//		if (c.isDatabaseLocal()) {
//			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
//			db = emf.createEntityManager();
//		} else {
//			Map<String, String> properties = new HashMap<>();
//			properties.put("javax.persistence.jdbc.user", c.getUser());
//			properties.put("javax.persistence.jdbc.password", c.getPassword());
//
//			emf = Persistence.createEntityManagerFactory(
//					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);
//			db = emf.createEntityManager();
//		}
//		System.out.println("DataAccess opened => isDatabaseLocal: " + c.isDatabaseLocal());
//
//	}
//
//	public Driver getDriverByEmail(String email) {
//		db.getTransaction().begin(); // Iniciar transacción (opcional, dependiendo del contexto)
//		try {
//			TypedQuery<Driver> query = db.createQuery("SELECT d FROM Driver d WHERE d.email = :email", Driver.class);
//			query.setParameter("email", email);
//			Driver driver = query.getSingleResult(); // Obtener el resultado único
//			db.getTransaction().commit(); // Confirmar transacción
//			return driver;
//		} catch (Exception e) {
//			db.getTransaction().rollback(); // Revertir transacción en caso de error
//			return null; // Manejo de errores (puede mejorarse lanzando una excepción personalizada)
//		}
//	}
//
//	public Passenger getPassengerByEmail(String email) {
//	    db.getTransaction().begin(); // Iniciar transacción (opcional según contexto)
//	    try {
//	        TypedQuery<Passenger> query = db.createQuery("SELECT p FROM Passenger p WHERE p.email = :email", Passenger.class);
//	        query.setParameter("email", email);
//	        Passenger passenger = query.getSingleResult(); // Obtener el resultado único
//	        db.getTransaction().commit(); // Confirmar transacción
//	        return passenger;
//	    } catch (Exception e) {
//	        db.getTransaction().rollback(); // Revertir en caso de error
//	        return null; // Manejo de errores (puede mejorarse lanzando una excepción personalizada)
//	    }
//	}
//	public void close() {
//		db.close();
//		System.out.println("DataAcess closed");
//	}

//	public List<Ride> getClientRides(Passenger user){
//
//
//		List<Ride> res = new ArrayList<>();
//		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r ", Ride.class);
//		
//		List<Ride> rides = query.getResultList();
//		for (Ride ride : rides) {
//			res.add(ride);
//		}
//		return res;
//	}

//=======
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Credentials;
import domain.Driver;
import domain.Passenger;
import domain.Ride;
import domain.Tarjeta;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	private EntityManager db;
	private EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess() {
		if (c.isDatabaseInitialized()) {
			String fileName = c.getDbFilename();

			File fileToDelete = new File(fileName);
			if (fileToDelete.delete()) {
				File fileToDeleteTemp = new File(fileName + "$");
				fileToDeleteTemp.delete();

				System.out.println("File deleted");
			} else {
				System.out.println("Operation failed");
			}
		}
		open();
		if (c.isDatabaseInitialized())
			initializeDB();

		System.out.println("DataAccess created => isDatabaseLocal: " + c.isDatabaseLocal() + " isDatabaseInitialized: "
				+ c.isDatabaseInitialized());

		close();

	}

	public DataAccess(EntityManager db) {
		this.db = db;
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 1;
				year += 1;
			}

			// Create drivers
			Driver driver1 = new Driver("driver1@gmail.com", "Aitor Fernandez");
			Driver driver2 = new Driver("driver2@gmail.com", "Ane Gaztañaga");
			Driver driver3 = new Driver("driver3@gmail.com", "Test driver");

			// Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year, month, 6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 4, 4);

			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year, month, 7), 4, 8);

			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year, month, 6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 14), 1, 3);

			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);
			
			//cretae cards
			Tarjeta t1=new Tarjeta(1,1,String.valueOf(1),String.valueOf(1),200);
			Tarjeta t2=new Tarjeta(2,1,String.valueOf(1),String.valueOf(1),0);
			Tarjeta t3=new Tarjeta(3,1,String.valueOf(1),String.valueOf(1),20);

			db.persist(t1);
			db.persist(t2);
			db.persist(t3);
			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns all the cities where rides depart
	 * 
	 * @return collection of cities
	 */
	public List<String> getDepartCities() {
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
		List<String> cities = query.getResultList();
		return cities;

	}

	// Para añadir usuarios TIPO: P-> Passenger | R-> rider
	public void addUser(String email, String name, Character tipo) {
		if (tipo.equals('P')) {
			Passenger client = new Passenger(name, email);
			db.getTransaction().begin();
			db.persist(client);
			db.getTransaction().commit();
		} else if (tipo.equals('D')) {
			Driver driver = new Driver(email, name);
			db.getTransaction().begin();
			db.persist(driver);
			db.getTransaction().commit();
		} else {
			System.out.println("Error");
		}

	}

	public void addCredential(String mail, String psw, Character tipe) {
		Credentials cred = new Credentials(mail, psw, tipe);
		db.getTransaction().begin();
		db.persist(cred);
		db.getTransaction().commit();

	}

	public List<Credentials> getCredentials() {

		TypedQuery<Credentials> query = db.createQuery("SELECT DISTINCT r FROM Credentials r ORDER BY r ",
				Credentials.class);
		List<Credentials> cred = query.getResultList();

		return cred;
	}

	/**
	 * This method returns all the arrival destinations, from all rides that depart
	 * from a given city
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from) {
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",
				String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList();
		return arrivingCities;

	}

	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from        the origin location of a ride
	 * @param to          the destination location of a ride
	 * @param date        the date of the ride
	 * @param nPlaces     available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today
	 * @throws RideAlreadyExistException         if the same ride already exists for
	 *                                           the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail)
			throws RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= " + from + " to= " + to + " driver=" + driverEmail
				+ " date " + date);
		try {
			if (new Date().compareTo(date) > 0) {
				throw new RideMustBeLaterThanTodayException(
						ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();

			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(
						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price);
			// next instruction can be obviated
			db.persist(driver);
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}

	}

	/**
	 * This method retrieves the rides from two locations on a given date
	 * 
	 * @param from the origin location of a ride
	 * @param to   the destination location of a ride
	 * @param date the date of the ride
	 * @return collection of rides
	 */
	public ArrayList<Ride> getAllRides() {
		ArrayList<Ride> res = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride ", Ride.class);
		ArrayList<Ride> rides = (ArrayList<Ride>) query.getResultList();
		for (Ride ride : rides) {
			res.add(ride);
		}
		return res;
	}
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= " + from + " to= " + to + " date " + date);

		List<Ride> res = new ArrayList<>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",
				Ride.class);
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
		for (Ride ride : rides) {
			res.add(ride);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param from the origin location of a ride
	 * @param to   the destination location of a ride
	 * @param date of the month for which days with rides want to be retrieved
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",
				Date.class);

		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			res.add(d);
		}
		return res;
	}

	public void open() {

		String fileName = c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);
			db = emf.createEntityManager();
		}
		System.out.println("DataAccess opened => isDatabaseLocal: " + c.isDatabaseLocal());

	}

	public Driver getDriverByEmail(String email) {
		db.getTransaction().begin(); // Iniciar transacción (opcional, dependiendo del contexto)
		try {
			TypedQuery<Driver> query = db.createQuery("SELECT d FROM Driver d WHERE d.email = :email", Driver.class);
			query.setParameter("email", email);
			Driver driver = query.getSingleResult(); // Obtener el resultado único
			db.getTransaction().commit(); // Confirmar transacción
			return driver;
		} catch (Exception e) {
			db.getTransaction().rollback(); // Revertir transacción en caso de error
			return null; // Manejo de errores (puede mejorarse lanzando una excepción personalizada)
		}
	}

	public Passenger getPassengerByEmail(String email) {
	    db.getTransaction().begin(); // Iniciar transacción (opcional según contexto)
	    try {
	        TypedQuery<Passenger> query = db.createQuery("SELECT p FROM Passenger p WHERE p.email = :email", Passenger.class);
	        query.setParameter("email", email);
	        Passenger passenger = query.getSingleResult(); // Obtener el resultado único
	        db.getTransaction().commit(); // Confirmar transacción
	        return passenger;
	    } catch (Exception e) {
	        db.getTransaction().rollback(); // Revertir en caso de error
	        return null; // Manejo de errores (puede mejorarse lanzando una excepción personalizada)
	    }
	}
	public void close() {
		db.close();
		System.out.println("DataAcess closed");
	}

	//mod (el rideFromDB)
		public boolean requestReservation(String passengerEmail, Ride ride) {
			db.getTransaction().begin();
			Passenger passenger = db.find(Passenger.class, passengerEmail); // Obtener el objeto Passenger de la base de
			Ride rideFromDB = db.find(Ride.class, ride.getRideNumber());														// datos
			if (passenger != null) { // Verificar si se encontró el pasajero
				if (rideFromDB.getnPlaces() > 0) { // Verificar si hay plazas disponibles en el viaje
					if (rideFromDB.addBook(passenger)) { // Intentar realizar la reserva para el pasajero
						rideFromDB.setBetMinimum(rideFromDB.getnPlaces() - 1); // Actualizar el número de plazas disponibles en el viaje
						passenger.addReserva(rideFromDB); 
							
						db.merge(passenger);
						db.merge(rideFromDB);
						
						db.getTransaction().commit();
						return true; // Reserva exitosa
					} else {
						db.getTransaction().rollback();
						return false; // No se pudo realizar la reserva (quizás no había plazas disponibles)
					}
				} else {
					db.getTransaction().rollback();
					return false; // No hay plazas disponibles en el viaje
				}
			} else {
				db.getTransaction().rollback();
				return false; // Si no se encuentra el pasajero, no se puede realizar la reserva
			}
		}
	public List<Ride> getBookingsForDriver(Driver driver) {
	    EntityManager em = emf.createEntityManager();
	    List<Ride> bookings = null;

	    try {
	        TypedQuery<Ride> query = em.createQuery(
	        		"SELECT b FROM Ride b WHERE b.driver = :driver", Ride.class);
	        query.setParameter("driver", driver);
	        bookings = query.getResultList();
	    } finally {
	        em.close();
	    }

	    return bookings;
	}
	public boolean doesCardExist(int nTarjeta) {
	    EntityManager em = emf.createEntityManager();
	    try {
	    	
	        TypedQuery<Tarjeta> query = em.createQuery(
	        		"SELECT t FROM Tarjeta t WHERE t.nTarjeta = :nTarjeta", Tarjeta.class);
	    	query.setParameter("nTarjeta", nTarjeta);
	    	Tarjeta t=query.getSingleResult();
	    	System.out.println("Tarjeta encontrada: " + t.getnTarjeta());
	        

	        return true;
	    } catch (NoResultException e) {
	        System.out.println(" No se encontró ninguna tarjeta con el número: " + nTarjeta);
	        return false;
	    } finally {
	        em.close();
	    }
	}
	public boolean doesCardHaveEnoughMoney(int nTarjeta, double cantidad) {
	    EntityManager em = emf.createEntityManager();
	    try {
	    	
	        TypedQuery<Tarjeta> query = em.createQuery(
	        		"SELECT t FROM Tarjeta t WHERE t.nTarjeta = :nTarjeta", Tarjeta.class);
	    	query.setParameter("nTarjeta", nTarjeta);
	    	Tarjeta t=query.getSingleResult();
	    	System.out.println("Tarjeta encontrada: " + t.getnTarjeta());
	        if(t.getCantidad()>=cantidad) {
	        	t.setCantidad(t.getCantidad()-cantidad);
	        	return true;
	        }else {
	        	return false;
	        }

	        
	    } catch (NoResultException e) {
	        System.out.println(" No se encontró ninguna tarjeta con el número: " + nTarjeta);
	        return false;
	    } finally {
	        em.close();
	    }
	}
//	public void deleteBooked() {
//		db.getTransaction().begin();
//		//TODO
//		
//		
//		
//		db.getTransaction().commit();
//	}
	public void insertRide(Ride ride) {
		db.getTransaction().begin();
		db.persist(ride);
		db.getTransaction().commit();
	}
	public void aproveRide(Ride ride, Passenger p) {
		//Obtenemos el ride de la BD para no tener problemas con el merge
		db.getTransaction().begin();
		Ride rideFromDB = db.find(Ride.class, ride.getRideNumber());
		Passenger[] pArray = rideFromDB.getBooked(); 
		int i=0;
		boolean exit = false;
		while(i < rideFromDB.getnPlaces() || !exit) {
			if(pArray[i].getEmail().equals(p.getEmail())) {
				exit = true;
				rideFromDB.setStatus(i, true);
			}
			
		}
		db.merge(rideFromDB);
		db.getTransaction().commit();
	}
	public void anularReserva(Passenger p, Ride ride) {
		db.getTransaction().begin();
		Ride rideFromDB = db.find(Ride.class, ride.getRideNumber());
		Passenger passenger = db.find(Passenger.class, p.getEmail());
		passenger.retirarReserva(rideFromDB);
		rideFromDB.retireBook(passenger);
		db.merge(rideFromDB);
		db.merge(passenger);
		
		db.getTransaction().commit();
	}

//>>>>>>> branch 'master' of https://github.com/aecheveste018/Rides
}

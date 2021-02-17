package repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.BigName;
import Entity.City;

	/*
	 *	Fetching values from the DB and 
	 * 	making the class embeddable to create Composition
	 */
	public class DummyRepoForCity {
	
	public static void main(String[] args) {

		City addCity = new City();
		addCity.setId(1);
			BigName addnames = new BigName();
			addnames.setNewName("Kolkata");
			addnames.setOldName("Calcutta");
		addCity.setNames(addnames);
		addCity.setArea("1,484 km²");
		addCity.setPopulation("1.9 crores");
		
		City addCityL1 = new City();
		addCityL1.setId(2);
			BigName some = new BigName();
			some.setNewName("Chennai");
			some.setOldName("Madras");
		addCityL1.setNames(some);
		addCityL1.setArea("690 km²");
		addCityL1.setPopulation("643 lakhs");
		
		City addCityL2 = new City();
		addCityL2.setId(3);
			BigName few = new BigName();
			few.setNewName("Mumbai");
			few.setOldName("Bombay");
		addCityL2.setNames(few);
		addCityL2.setArea("1,092 km²");
		addCityL2.setPopulation("1.3 crores");

		Configuration configObj = new Configuration().configure().addAnnotatedClass(City.class);
		
		ServiceRegistry serviceRegistryObj = 
				new ServiceRegistryBuilder()
				.applySettings(configObj.getProperties())
				.buildServiceRegistry();
		
		SessionFactory sessionfactoryObj = configObj.buildSessionFactory(serviceRegistryObj); 
		Session sessionObj = sessionfactoryObj.openSession();
		
		Transaction savetransaction = sessionObj.beginTransaction();
			sessionObj.save(addCity);
			sessionObj.save(addCityL1);
			sessionObj.save(addCityL2);
		savetransaction.commit();
		
		System.out.println("Inserted the City into DB -> "+ addCity);
		System.out.println("Inserted the City into DB -> "+ addCityL1);
		System.out.println("Inserted the City into DB -> "+ addCityL2);
		
		Transaction transactionObj = sessionObj.beginTransaction();
		
		City fetch1 = new City();
		fetch1 = (City) sessionObj.get(City.class, 1);
		
		City fetch2 = new City();
		fetch2 = (City) sessionObj.get(City.class, 2);
		
		City fetch3 = new City();
		fetch3 = (City) sessionObj.get(City.class, 3);
		transactionObj.commit();
		
		System.out.println("\nFetched Kolkata from DB -> "+ fetch1);
		System.out.println("Fetched Chennai from DB -> "+ fetch2);
		System.out.println("Fetched Mumbai from DB -> "+ fetch3);
	
	}
	
	
}

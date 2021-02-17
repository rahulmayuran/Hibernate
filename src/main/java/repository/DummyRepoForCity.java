package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Entity.City;

/*
 * Persisting the City object which is 
 * 1. Defined as a static variable
 * 2. Initialized using setters
 * 3. persisted in Database by save method
 */
public class DummyRepoForCity {

	static City cityobjblock;
	
	static {
		
		cityobjblock = new City();
		cityobjblock.setName("Mumbai");
		cityobjblock.setArea("603.4 sqkm");
		cityobjblock.setPopulation("1.84 crores");
		System.out.println("Setting city object values in static block");
	}
	
	public static City CitySetter(City city) 
	{
		City cityobj = new City();
		cityobj.setId(2);
		cityobj.setName("Chennai");
		cityobj.setArea("426 km²");
		cityobj.setPopulation("70.9 lakhs");
		return cityobj;
	}
	
	public static void main(String[] args) {
		
		//An empty city object
		City emptycity = new City();
		
		System.out.println("City Object before passed to static block -> " + emptycity);
		System.out.println("From static block "+ cityobjblock );
		System.out.println("The city object is "+ CitySetter(emptycity));
		
		
		Configuration configObj = new Configuration().configure().addAnnotatedClass(City.class);
		SessionFactory sessionfactoryObj = configObj.buildSessionFactory(); 
		Session sessionObj = sessionfactoryObj.openSession();
		
		Transaction transactionObj = sessionObj.beginTransaction();
		
		//
		sessionObj.save(CitySetter(emptycity));
		sessionObj.save(emptycity);
		sessionObj.save(cityobjblock);
		
		transactionObj.commit();

	}
	
	
}

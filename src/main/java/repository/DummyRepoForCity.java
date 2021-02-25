package repository;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.City;

	/*
	 * Persisting the City object which is 
	 * 1. Defined as a static variable
	 * 2. Initialized using setters
	 * 3. persisted in Database by save method
	 */
	public class DummyRepoForCity {

	static City cityobjblock;
	
	/*
	 * Code inside static block is executed only once: the first time the class is loaded into memory
	 * Static blocks are executed before constructors.
	 */
	static {
		
		cityobjblock = new City();
		cityobjblock.setName("Mumbai");
		cityobjblock.setArea("603.4 sqkm");
		cityobjblock.setPopulation(18400000L);
		System.out.println("Setting city object values in static block");
	}
	
	public static City CitySetter(City city) 
	{
		City cityobj = new City();
		cityobj.setId(2);
		cityobj.setName("Chennai");
		cityobj.setArea("426 km²");
		cityobj.setPopulation(7090000L);
		return cityobj;
	}
	
	public static void main(String[] args) {
		
		//An empty city object
		City emptycity = new City();
		
		System.out.println("City Object before passed to static block -> " + emptycity);
		System.out.println("From static block "+ cityobjblock );
		System.out.println("The city object is "+ CitySetter(emptycity));
		
		
		Configuration configObj = new Configuration().configure().addAnnotatedClass(City.class);
		
		ServiceRegistry srcObj = new ServiceRegistryBuilder().applySettings(configObj.getProperties()).buildServiceRegistry();
		
		SessionFactory sessionfactoryObj = configObj.buildSessionFactory(srcObj);
		
		Session sessionObj = sessionfactoryObj.openSession();
		
		Transaction transactionObj = sessionObj.beginTransaction();
		
		//Persisting city object which is defined in static method of City type
		sessionObj.save(CitySetter(emptycity));
		
		//Persisting city object which is instantiated and NULL values reflect in DB
		sessionObj.save(emptycity);
		
		//Persisting city object which is declared in static block/variable of City type
		sessionObj.save(cityobjblock);
		
		//WON'T WORK- Persisting city object which is declared in static block/variable of City type
		sessionObj.save(cityobjblock);

//To insert 10 records in the Database
		Random rm = new Random();
		
		for(int i=0 ; i<10; i++) {
			City iterate = new City();
			iterate.setId(i);
			iterate.setPopulation(rm.nextInt(10000000));
			iterate.setName("Name "+i);
			iterate.setArea("Area "+i);
			sessionObj.save(iterate);
		}
		
		transactionObj.commit();

//Opening a new Session to perform all fetchings
		Session newSession = sessionfactoryObj.openSession();

/*
 * To fetch the full list of Cities 
 */
			newSession.beginTransaction();
			Query fetchAll = newSession.createQuery("from City");
			List<City> cityList = fetchAll.list();
			
			for(City c : cityList) {System.out.println(c);}

/*
 * To get a List of value based on condition
 */
			Query fetchMorePop = newSession.createQuery("from City where population>9000000");
			List<City> cityWithMorePopulation = fetchMorePop.list();
			
			int counter = 0;
			for(City ci : cityWithMorePopulation) {
				++counter; 
				System.out.println("Cities with more population"+ci);
			}
			System.out.println("\nCities with more than 9 million population-> "+counter);
			
/*
 * To fetch a single Record using HQL
 */
			Query fetchSingle = newSession.createQuery("from City where area='null'");
			City fetchRecord = (City) fetchSingle.uniqueResult();
			
			System.out.println("The only empty record in database -> "+fetchRecord);
			

	}
	
	
}

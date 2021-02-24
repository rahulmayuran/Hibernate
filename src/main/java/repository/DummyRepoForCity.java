package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.City;

	/*
	 * 2nd example, with same static method
	 * but using ServiceRegistry Interface as buildSessionFactory is deprecated and 
	 * expecting service registry object
	 */
	public class DummyRepoForCity {

	
	public static City citySetter(City methodCity) 
	{
		City cityInsideMethod = new City();
		cityInsideMethod.setId(3);
		cityInsideMethod.setName("Kolkata");
		cityInsideMethod.setArea("206.1 km²");
		cityInsideMethod.setPopulation("1.49 crores");
		return cityInsideMethod;
	}
	
	public static void main(String[] args) {
		
		City cityfromMainblock = new City();
		System.out.println("The city object is "+ citySetter(cityfromMainblock));
		
		
Configuration configObj = new Configuration().configure().addAnnotatedClass(City.class);
System.out.println("The Configuration object is "+ configObj.getClass());
		
	ServiceRegistry serviceRegistryObj = 
			new ServiceRegistryBuilder()
			.applySettings(configObj.getProperties())
			.buildServiceRegistry();
	
	System.out.println("The Service-Registry object is "+ serviceRegistryObj.getClass());
		
		SessionFactory sessionfactoryObj = configObj.buildSessionFactory(serviceRegistryObj); 
		System.out.println("The SessionFactory object is "+ sessionfactoryObj.getClass());
		
			Session sessionObj = sessionfactoryObj.openSession();
			System.out.println("The Session object is "+ sessionObj.getClass());
		
				Transaction transactionObj = sessionObj.beginTransaction();
				transactionObj.setTimeout(5);
				System.out.println("The configuration object is "+ transactionObj);
				System.out.println("The timeout for Transaction object is "+ transactionObj.getTimeout());
		
				//Persisting city object which is defined in static method of City type
				sessionObj.save(citySetter(cityfromMainblock));
				
				/*
				 * Fetching the Name of the city twice, Hibernate has Default FIRST LEVEL Caching
				 * Eventhough, you used get method twice, only one query is executed
				 */
				City fetchname = (City) sessionObj.get(City.class, 1);
				City fetchnametwice = (City) sessionObj.get(City.class, 1);
				
				System.out.println("Name of City from get method -> " + fetchname.getName());
				System.out.println("Name of City fetched again from get method -> " + fetchnametwice.getName());
	
				transactionObj.commit();
				sessionObj.close();
				
				/*
				 * Created another session and again fetching the name of City.
				 * This time, a new query is fired.
				 * 
				 * That's the reason for SECOND LEVEL Cache to pitch in.
				 * Now, it will only print one query, after the following changes
				 *  1. Including net.sf.ehcache dependency
				 *  2. Including hibernate-ehcache-{same version of core}
				 *  3. in cfg.xml file, mention second-level cache as true & provide the cache class name under region factory class
				 *  4. Mark entity class which is cached as @Cacheable and @cache with usage as READ_only
				 *  
				 */
				Session secondFetch = sessionfactoryObj.openSession();
				
				City fetchedInAnotherSession = (City) secondFetch.get(City.class, 1);
				System.out.println("Name of the city fetched from another session -> " + fetchedInAnotherSession.getName());
				
				secondFetch.close();
	}
	
	
	
}

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
	
				transactionObj.commit();

	}
	
	
	
}

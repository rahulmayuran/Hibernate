package repository;

import org.hibernate.Query;
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
	
	

	public static void main(String[] args) {
		
		
		City cityInsideMethod = new City();
			cityInsideMethod.setName("Kolkata");
			cityInsideMethod.setArea("206.1 km²");
			cityInsideMethod.setPopulation("1.49 crores");
		
		//City cityfromMainblock = new City();
		System.out.println("The city object is "+ cityInsideMethod);
		
		City fetchname;
		
Configuration configObj = new Configuration().configure().addAnnotatedClass(City.class);
		
	ServiceRegistry serviceRegistryObj = 
			new ServiceRegistryBuilder()
			.applySettings(configObj.getProperties())
			.buildServiceRegistry();
			
		SessionFactory sessionfactoryObj = configObj.buildSessionFactory(serviceRegistryObj); 
		
			Session sessionObj = sessionfactoryObj.openSession();	
			
				Transaction transactionObj = sessionObj.beginTransaction();
				/*
				 * Fetching the Name of the city twice, Hibernate has Default FIRST LEVEL Caching
				 * Eventhough, you used get method twice, only one query is executed
				 */
				Query fetchquery = sessionObj.createQuery("from City where id=1");
				fetchquery.setCacheable(true);
				fetchname = (City) fetchquery.uniqueResult();
				
				Query fetchqueryinSameSession = sessionObj.createQuery("from City where id=1");
				fetchqueryinSameSession.setCacheable(true);
				fetchname = (City) fetchqueryinSameSession.uniqueResult();
					
				System.out.println("Name of City from get method -> " + fetchname.getName());	
				System.out.println("Name of City from get method in Same session -> " + fetchname.getName());	

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
				 *  5.This works only for get() method of hibernate, it will not work for query methods,
				 *  To solve,this you have to mention cache-query true in cfg and here you need to setCacheable as true
				 */
			Session secondFetch = sessionfactoryObj.openSession();
				
				secondFetch.beginTransaction();
				
				Query fetchqueryAgain = secondFetch.createQuery("from City where id=1");
				fetchqueryAgain.setCacheable(true);
				fetchname = (City) fetchqueryAgain.uniqueResult();
		
				System.out.println("Name of the city fetched from another session -> " + fetchname.getName());
			
			secondFetch.getTransaction().commit();
			
			secondFetch.close();
	}
	
	
	
}

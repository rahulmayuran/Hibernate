package repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.BigName;
import Entity.City;
import Entity.People;

	/*
	 * Set the city object values first and then 
	 * inside people object, set the city objects, as it creates a common reference
	 */
	public class DummyRepoForCity {
	
	public static void main(String[] args) {

		/* 
		 *
		 */
		City addCity = new City();
			addCity.setCityId(110);
				BigName addnames = new BigName();
				addnames.setNewName("Kolkata");
				addnames.setOldName("Calcutta");
			addCity.setNames(addnames);
			addCity.setArea("1,484 km²");
			addCity.setPopulation("1.9 crores");
		
		People people = new People();
		
		//Setting mobile Id is useless, you marked Generation type as identity
			people.setMobId(112L);
			people.setFamousFor("Hilsa Fish");
			people.setFemales(23215435);
			people.setMales(43215435);
			people.setNoOfLiterates(1321543);
			people.setCity(addCity);
			
		People anothersetofpeople = new People();
		
		//Setting mobile Id is useless, you marked Generation type as identity	
			anothersetofpeople.setMobId(101L);
			anothersetofpeople.setFamousFor("Howrah Bridge");
			anothersetofpeople.setFemales(432432554);
			anothersetofpeople.setMales(838274922);
			anothersetofpeople.setNoOfLiterates(3243027);
			anothersetofpeople.setCity(addCity);
		
		/*
		 * saving sessions for both objects and committing the transaction
		 */
		Configuration configObj = new Configuration()
								.configure()
								.addAnnotatedClass(City.class)
								.addAnnotatedClass(People.class);
		
		ServiceRegistry serviceRegistryObj = 
				new ServiceRegistryBuilder()
				.applySettings(configObj.getProperties())
				.buildServiceRegistry();
		
		SessionFactory sessionfactoryObj = configObj.buildSessionFactory(serviceRegistryObj); 
		Session sessionObj = sessionfactoryObj.openSession();
		
			sessionObj.beginTransaction();
				sessionObj.save(people);
				sessionObj.save(anothersetofpeople);
				sessionObj.save(addCity);
			sessionObj.getTransaction().commit();
			sessionObj.close();
		
		System.out.println("\nInserted the City into DB -> "+ addCity);
		System.out.println("Inserted People into DB -> "+ people);
		System.out.println("Inserted another set of people into DB -> "+ anothersetofpeople);
		
//Printing the foreign keys
		System.out.println("\nForeign key for people instance with City table " + people.getCity().getCityId());
		System.out.println("\nForeign key for anothersetofpeople instance with City table " + anothersetofpeople.getCity().getCityId());
	
/*
 * Creating another session for implementing LAZY FETCHING
 */
		Session lazyAndEagerLoading = sessionfactoryObj.openSession();
		
			lazyAndEagerLoading.beginTransaction();
			
			City lazyCity = (City) lazyAndEagerLoading.get(City.class, 110);
			People lazypeople = (People) lazyAndEagerLoading.get(People.class, 1L);
		
//Select query is triggered for only city class, It doesn't care about People entity
			System.out.println("\nArea  from People ref to City is " + lazypeople.getCity().getArea());
			
//Select query is triggered for people and city entities with a left outer join city on P.cityId= C.cityId
//It is lazy loading, because, unless you ask for it, you won't get it.
			System.out.println("\nPopulation in City is " + lazyCity.getPopulation());
			
//Select query is trigerred from One-To-Many people entity 			
			Collection<People> peopleSet = lazyCity.getPeople();
			for(People p: peopleSet) {System.out.println("\n list of people -> "+p);}
			
//This transaction is not rolled back, and it doesn't care to be committed/rolled back, why?
			lazyAndEagerLoading.getTransaction().rollback();
			lazyAndEagerLoading.close();
			
	}
	
	
}

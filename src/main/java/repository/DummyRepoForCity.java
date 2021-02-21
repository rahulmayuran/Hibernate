package repository;

import java.util.HashSet;
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
	 *A Repository class to achieve ManyToMany mappping 
	 *	Creating two instances of People object and setting values to it and 
	 *	adding it to Setter of City class which holds the set of People object
	 *
	 *	Creating one instance of City object and setting values normally along with Embeddable class
	 *	Adding the people instance as HashSets and passing it to the setter of City class as mentioned above
	 */
	public class DummyRepoForCity {
	
	public static void main(String[] args) {

		People people = new People();
		
			people.setMobId(112L);
			people.setFamousFor("Hilsa Fish");
			people.setFemales(23215435);
			people.setMales(43215435);
			people.setNoOfLiterates(1321543);
		
		
		People anothersetofpeople = new People();
			
			anothersetofpeople.setMobId(101L);
			anothersetofpeople.setFamousFor("Howrah Bridge");
			anothersetofpeople.setFemales(23215535);
			anothersetofpeople.setMales(43215535);
			anothersetofpeople.setNoOfLiterates(1321643);
			
		/*
		 * Adding another pair of people sets to be appended to Citys' Set
		 */
		People chennaiPeople = new People();
		
			chennaiPeople.setFamousFor("Marina");
			chennaiPeople.setMobId(201L);
			chennaiPeople.setNoOfLiterates(8405080);
			chennaiPeople.setMales(6704200);
			chennaiPeople.setFemales(45002500);
			
		People anotherSetOfChennaiPeople = new People();
			
			anotherSetOfChennaiPeople.setFamousFor("NoCaste");
			anotherSetOfChennaiPeople.setMobId(211L);
			anotherSetOfChennaiPeople.setNoOfLiterates(8405180);
			anotherSetOfChennaiPeople.setMales(6704300);
			anotherSetOfChennaiPeople.setFemales(45002600);
			
		City addCity = new City();
			addCity.setCityId(110);
				BigName addnames = new BigName();
				addnames.setNewName("Kolkata");
				addnames.setOldName("Calcutta");
			addCity.setNames(addnames);
			addCity.setArea("1,484 km²");
			addCity.setPopulation("1.9 crores");
			
			Set<People> peopleSet = new HashSet<People>();
				peopleSet.add(people);
				peopleSet.add(anothersetofpeople);
			
			addCity.setPeople(peopleSet);
			
			/*
			 * Creating another city object to add another pair of people
			 */
		City anotherCity = new City();
			anotherCity.setCityId(220);
				BigName newCityName = new BigName();
				newCityName.setNewName("Chennai");
				newCityName.setOldName("Madras");
			anotherCity.setNames(newCityName);
			anotherCity.setArea("1,092 km²");
			anotherCity.setPopulation("1.1 crore");	
			
			Set<People> cheannaiPeopleSet = new HashSet<People>();
				cheannaiPeopleSet.add(chennaiPeople);
				cheannaiPeopleSet.add(anotherSetOfChennaiPeople);
			
			anotherCity.setPeople(cheannaiPeopleSet);
		
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
		
		/*
		 * Committing another transaction for persisting the people objects and
		 * appending it to the Set created in City object
		 */
		sessionObj.beginTransaction();
			sessionObj.save(chennaiPeople);
			sessionObj.save(anotherSetOfChennaiPeople);
			sessionObj.save(anotherCity);
		sessionObj.getTransaction().commit();
		
		sessionObj.close();
		
		System.out.println("\nInserted the City into DB -> "+ addCity);
		System.out.println("Inserted People into DB -> "+ people);
		System.out.println("Inserted another set of people into DB -> "+ anothersetofpeople);
		System.out.println("\nInserted the City into DB -> "+ anotherCity);
		System.out.println("Inserted People into DB -> "+ chennaiPeople);
		System.out.println("Inserted another set of people into DB -> "+ anotherSetOfChennaiPeople);
	}
	
	
}

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
 * A Repository class to achieve ManyToMany mappping 
 * 
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
			anothersetofpeople.setFemales(73215535);
			anothersetofpeople.setMales(83215535);
			anothersetofpeople.setNoOfLiterates(43216432);
			
/*
 * Adding another pair of people sets to be appended to Citys' Set
 */
		People chennaiPeople = new People();
		
			chennaiPeople.setFamousFor("Marina");
			chennaiPeople.setMobId(201L);
			chennaiPeople.setNoOfLiterates(84050800);
			chennaiPeople.setMales(67042000);
			chennaiPeople.setFemales(45002500);
			
		People anotherSetOfChennaiPeople = new People();
			
			anotherSetOfChennaiPeople.setFamousFor("NoCaste");
			anotherSetOfChennaiPeople.setMobId(211L);
			anotherSetOfChennaiPeople.setNoOfLiterates(84051800);
			anotherSetOfChennaiPeople.setMales(67043000);
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
		
/*
 * Creating a Session object with the help of SessionFactory object
 */
		Session sessionObj = sessionfactoryObj.openSession();
		
//FIRST Transaction
		sessionObj.beginTransaction();
			sessionObj.save(people);
			sessionObj.save(anothersetofpeople);	
/*
 * If you set values in this way, 1484 will be updated to 1500, but it must be
 * with same session object. can be done in inside a Transaction this way
 * But it WONT BE PRINTED IN CONSOLE
 */
				addCity.setArea("1,500 km²");
			sessionObj.save(addCity);
		sessionObj.getTransaction().commit();
		
//SECOND Transaction
		sessionObj.beginTransaction();
//Another way to update records or in another transaction, 1500 will be updated to 1600, printed in Console.
			addCity.setArea("1,600 km²");
		sessionObj.save(addCity);
		
		sessionObj.getTransaction().commit();
		
//You have to clear the session, it has pending inserts, updates etc. If not you will get error of two open sessions
		sessionObj.clear();
		
//FAILING Transaction as it is an illegal attempt to associate collection with two open sessions.
		
		/*
		 * Session newSessionObj = sessionfactoryObj.openSession();
		 * newSessionObj.beginTransaction(); //Another way to update records, 1500 will
		 * be updated to 1600, printed in Console. addCity.setArea("1,600 km²");
		 * newSessionObj.save(addCity);
		 * 
		 * newSessionObj.getTransaction().commit();
		 */
		
/*
 * ABOUT saveOrUpdate method, In the previous scenario, when you tried to save a persistent
 * entity which is 'addCity' object into another session, you got duplicate key error. 
 * 
 * The solution for this problem is saveOrUpdate() method which is functioning like save() method
 * but it will persist the transient entities too.
 * 
 * Using a SECOND session
 * Select query and update query are fired in console.
 */
		Session testingSaveOrUpdateSession = sessionfactoryObj.openSession();
		
			testingSaveOrUpdateSession.beginTransaction();
			addCity.setArea("1,555 km²");
			testingSaveOrUpdateSession.saveOrUpdate(addCity);
			
			testingSaveOrUpdateSession.getTransaction().commit();
		testingSaveOrUpdateSession.close();
/*
 * THIRD TRANSACTION
 * To check what got updated in City Object
 * 
 * use load method of Session instance and pass the integer value of Id which you are using.
 * You will get the area field which gets updated in console.
 * 
 * Using THIRD session
 */
		Session sessionObjToCheckUpdated = sessionfactoryObj.openSession();
			sessionObjToCheckUpdated.beginTransaction();
				City updatedArea = (City) sessionObjToCheckUpdated.load(City.class, 110);
				System.out.println("Updated Record is " + updatedArea.getArea());
			sessionObjToCheckUpdated.getTransaction().commit();
		sessionObjToCheckUpdated.close();
		
/*
 * FOURTH TRANSACTION
 * Committing another transaction for persisting the people objects and
 * appending it to the Set created in City object
 */
		sessionObj.beginTransaction();
			sessionObj.save(chennaiPeople);
			sessionObj.save(anotherSetOfChennaiPeople);
			sessionObj.save(anotherCity);
		sessionObj.getTransaction().commit();
		
//Close the FIRST session
		sessionObj.close();
		
		System.out.println("\nInserted the City into DB -> "+ addCity);
		System.out.println("Inserted People into DB -> "+ people);
		System.out.println("Inserted another set of people into DB -> "+ anothersetofpeople);
		
		System.out.println("\nInserted the City into DB -> "+ anotherCity);
		System.out.println("Inserted People into DB -> "+ chennaiPeople);
		System.out.println("Inserted another set of people into DB -> "+ anotherSetOfChennaiPeople);
	}
	
	
}

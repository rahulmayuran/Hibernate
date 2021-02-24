package repository;

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
		
			people.setMobId(112L);
			people.setFamousFor("Hilsa Fish");
			people.setFemales(23215435);
			people.setMales(43215435);
			people.setNoOfLiterates(1321543);
			people.setCity(addCity);
			
		People anothersetofpeople = new People();
			
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
		
		System.out.println("\nInserted the City into DB -> "+ addCity);
		System.out.println("Inserted People into DB -> "+ people);
		System.out.println("Inserted another set of people into DB -> "+ anothersetofpeople);
		
		System.out.println("\nForeign key for people instance with City table " + people.getCity().getCityId());
		System.out.println("\nForeign key for anothersetofpeople instance with City table " + anothersetofpeople.getCity().getCityId());

/*
 * 1.Creating a Session object to perform EAGER loading, that is annotated in City's OneToMany argument
 * 
 * 2.The advantage here is, you didn't call people object, any getters of people or set of people. 
 * 		such that without an explicit call, all the DB values are fetched
 * 
 * 3. From the previous example of LAZY loading, where the committing/rollback is not necessary, The session is simply closed
 */
		Session eagerLoading = sessionfactoryObj.openSession();
			eagerLoading.beginTransaction();
			
			City eagerCity = (City) eagerLoading.get(City.class, 110);
			
			System.out.println("\nEager loading of People from City's getter -> " + eagerCity.getPeople());
			System.out.println("\nEager loading of City -> " + eagerCity.getNames());
			System.out.println("\nEager loading of both City/People -> " + eagerCity);
			
		eagerLoading.close();
	}
	
	
}

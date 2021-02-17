package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.BigName;
import Entity.City;
import Entity.People;

	/*
	 *	Fetching values from the DB and 
	 * 	making the class embeddable to create Composition
	 */
	public class DummyRepoForCity {
	
	public static void main(String[] args) {

		/* Refer the People instance as a setter in setPeople method
		 * to make foreign key reference.
		 * 112L will be a common field in both tables
		 */
		People people = new People();
			people.setFamousFor("Hilsa Fish");
			people.setFemales(23215435);
			people.setMales(43215435);
			people.setMobId(112L);
			people.setNoOfLiterates(1321543);
		
		/*
		 * Creating a City object that holds the names as composite columns
		 * created a reference to People class by passing the instance
		 */
		City addCity = new City();
			addCity.setId(1);
				BigName addnames = new BigName();
				addnames.setNewName("Kolkata");
				addnames.setOldName("Calcutta");
			addCity.setNames(addnames);
			addCity.setArea("1,484 km²");
			addCity.setPopulation("1.9 crores");
			addCity.setPeople(people);
			
		/*
		 * Inside Configuration > ServiceRegistry > SessionFactory > Session , A transaction happens
		 * As Configuration is the only implementation class and all others all interfaces.
		 * 
		 * If you have different cfg.xml file, then provide the new name under configure method
		 * all the entity classes that needs a table is marked under addAnnotatedClass method
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
		sessionObj.save(addCity);
		sessionObj.getTransaction().commit();
		
		System.out.println("Inserted the City into DB -> "+ addCity);
		System.out.println("Inserted People into DB -> "+ people);
	
	}
	
	
}

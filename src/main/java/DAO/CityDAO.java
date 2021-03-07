package DAO;

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
import Entity.CityState;

public class CityDAO {


	Configuration configObj = new Configuration().configure().addAnnotatedClass(City.class);
	
	ServiceRegistry srcObj = new ServiceRegistryBuilder().applySettings(configObj.getProperties()).buildServiceRegistry();
	
	SessionFactory sessionfactoryObj = configObj.buildSessionFactory(srcObj);
	
	public void PersistCity(City c) {
		
		Transaction transactionObj = null;
		try{
			
		Session sessionObj = sessionfactoryObj.openSession();
		
		transactionObj = sessionObj.beginTransaction();
		sessionObj.save(c);
		transactionObj.commit();
		
		}catch(Exception e) {
			transactionObj.rollback();
			e.getMessage();
		}
	}
	
	public void HQLinsertCity() {
		Transaction transaction = null;
		try  {
			// start a transaction
			Session session = sessionfactoryObj.openSession();
			transaction = session.beginTransaction();

			String hql = 
					"INSERT INTO City (area,cityState,name,population) "
					+ "SELECT area,cityState,name,population FROM City ";
					
					
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void RandomInsert() {
		
		Transaction transactionObj = null;
		try {
			Session sessionObj = sessionfactoryObj.openSession();
			
			transactionObj = sessionObj.beginTransaction();
		//To insert 10 records in the Database
				Random rm = new Random();
				
				for(int i=1 ; i<=10; i++) {
					City iterate = new City();
					iterate.setId(i);
					iterate.setPopulation(rm.nextInt(10000000));
					iterate.setName("City"+i);
					iterate.setArea(rm.nextInt(10000)+ " sqkm");
					iterate.setCityState(CityState.NEW);
					sessionObj.save(iterate);
				}
				
				transactionObj.commit();
				sessionObj.close();
		}
		catch (Exception e) {
			transactionObj.rollback();
			System.out.println();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<City> getCityList(){

		//Opening a new Session to perform all fetchings
		Session newSession = sessionfactoryObj.openSession();
		return newSession.createQuery("from City").list();
	}
	
	@SuppressWarnings("unchecked")
	public void getCitiesWithMorePopulation() {
		/*
		 * To get a List of value based on condition
		 */
		Session newSession = sessionfactoryObj.openSession();
		Query fetchMorePop = newSession.createQuery("from City where population>9000000");
		List<City> cityWithMorePopulation = fetchMorePop.list();
		
		cityWithMorePopulation.forEach((s)->System.out.println(s));
		
		int counter = 0;
		for(City x: cityWithMorePopulation) {
			++counter; 
			x.getClass();
		}
		System.out.println("\nCities with more than 9 million population-> "+counter);
	}
	
	public void getSingleCity() {
		/*
		 * To fetch a single Record using HQL
		 */
		Session newSession = sessionfactoryObj.openSession();
		Query fetchSingle = newSession.createQuery("from City where area='null'");
		City fetchRecord = (City) fetchSingle.uniqueResult();
		
		System.out.println("The only empty record in database -> "+fetchRecord);
	}
	
	public void HQLSelectCity(){
		/*
		 * Using java variables to set values
		 */
			long pop = 9000000L;
			
//Using :operator and setParameter("",var) to bind that variable  
//Can use both CreateQuery and CreateSQLQuery, but * will not work in CreateQuery
			Session newSession = sessionfactoryObj.openSession();
			Query fetchSQLQuery = newSession.createSQLQuery("Select * from City where population> :popl");
			fetchSQLQuery.setParameter("popl", pop);

//Should always be an Object array
			List<Object[]> citySQLList = (List<Object[]>)fetchSQLQuery.list();
			
			int count = 0;

//Double for loops to go deep and fetch records
			for(Object[] csql: citySQLList) {
				count++;
				for(Object csqlcsql : csql) {
					
					System.out.println(csqlcsql);
				}
				System.out.println("City--*--Information *--->ID: "+csql[0]+" Area: "+csql[1]+ " CityState: "+csql[2]+" Name: "+csql[3]+" Population: "+csql[4]);
			}
			System.out.println("\nFetched Records as Dynamic Query with count of ->" + count);
			newSession.close();
	}
	
	public City getHQLCity(int id) {

		Transaction transaction = null;
		City hqlcity = null;
		try {
			// start a transaction
			Session session = sessionfactoryObj.openSession();
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM City C WHERE C.id = :CityId";
			Query query = session.createQuery(hql);
			query.setParameter("CityId", id);
			List<City> results = query.list();
			
			System.out.println(results);
			
			if (results != null && !results.isEmpty()) {
				hqlcity = (City) results.get(0);
			}
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return hqlcity;
	}
	
	public void deleteHQLCity(int id) {

		Transaction transaction = null;
		try {
			// start a transaction
			Session session = sessionfactoryObj.openSession();
			transaction = session.beginTransaction();

			// Delete a student object
			City deletecity = (City) session.load(City.class, id);
			System.out.println("City with id -> "+deletecity.getId()+" About to get Deleted..... ByeBye");
			
			if (deletecity != null) {
				String hql = "DELETE FROM City " + "WHERE id = :ZitieeID";
				Query query = session.createQuery(hql);
				query.setParameter("ZitieeID", id);
				
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
				
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}

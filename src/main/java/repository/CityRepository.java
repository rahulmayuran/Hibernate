package repository;

import java.util.List;

import DAO.CityDAO;
import Entity.City;
import Entity.CityState;

	public class CityRepository {

	
	public static void main(String[] args) {

		CityDAO daoObject = new CityDAO();
		
		City addCity = new City(101,"Delhi","2890 sqkm", 232473295L , CityState.METRO);
		City anotherCity = new City(102,"Mumbai","2660 sqkm", 322473293L , CityState.METRO);
		City yetAnotherCity = new City(103,"Kolkata","2345 sqkm", 112473293L , CityState.ANTIQUE);
	
		daoObject.PersistCity(addCity);
		daoObject.HQLinsertCity();
		
		daoObject.PersistCity(anotherCity);
		daoObject.PersistCity(yetAnotherCity);
		
		daoObject.RandomInsert();
		
		daoObject.getCitiesWithMorePopulation();
		
		// get CityList
		List<City> students = daoObject.getCityList();
		students.forEach(s -> System.out.println(s));
		
		daoObject.getSingleCity();
		
		daoObject.HQLSelectCity();
		
		daoObject.getHQLCity(7);
		
		daoObject.deleteHQLCity(5);
		
	}
	
	
}

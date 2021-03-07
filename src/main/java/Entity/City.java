package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	private String area;
	
	private long population;
	
	@Enumerated(EnumType.STRING)
	private CityState cityState;
	
	public City() {
		// TODO Auto-generated constructor stub
	}
	
	public City(int id, String name, String area, long population, CityState cityState) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.population = population;
		this.cityState = cityState;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(long i) {
		this.population = i;
	}
	
	public CityState getCityState() {
		return cityState;
	}
	public void setCityState(CityState cityState) {
		this.cityState = cityState;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", area=" + area + ", population=" + population +
				", CityState "+cityState +"]";
	}
	
	
}

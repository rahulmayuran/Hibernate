package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@Column(nullable = false)
	private String name;
	
	private String area;
	
	private long population;
	
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
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", area=" + area + ", population=" + population + "]";
	}
	
	
}

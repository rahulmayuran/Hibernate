package Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/*
 * The intention here is to make a foreign key reference to Person class with MobId column in Person class. 
 * Already there is a Composition, but as it is not a separate entity class/table, 
 * we can simply set values while commiting transaction.
 */
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigName names;
	
	private String area;
	
	private String population;
	
	/*
	 * This is the easiest mapping when compared to 3 other cousins.
	 * which simply creates its own column and foreign key with PK Id of other table.
	 */
	@OneToOne
	private People people;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public BigName getNames() {
		return names;
	}
	public void setNames(BigName names) {
		this.names = names;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ","
				+ " names=" + names + ","
				+ " area=" + area + ", "
				+ "population=" + population 
				+ ", people="+ people + "]";
	}
	
	
	
	
}

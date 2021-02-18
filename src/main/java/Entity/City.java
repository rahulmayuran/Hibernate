package Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * OneToMany annotation should be provided in one class that has a relationship 
 * with the other class, 
 * 
 * That field must be a Map ,List or basically a Collection object
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
	 * OneToMany can't be done with single entity object
	 * As you will get AnnotationException and illegal attempt to map a non-collection
	 * 
	 * So you're creating a list/map of this object 
	 * 
	 * If one entity is [City] and other Entity is [People], 
	 * For OneToOne [City] table has a extra column called City_MobId that has FK with PK of [People] table
	 * 
	 * But for OneToMany, A new table will be created -> [City_People] and it will have both City_Id and people_MobId 
	 */
	
	@OneToMany
	private Set<People> people = new HashSet<People>();
	
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
	public Set<People> getPeople() {
		return people;
	}
	public void setPeople(Set<People> people) {
		this.people = people;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", names=" + names + ", area=" + area + ", population=" + population + ", people="
				+ people + "]";
	}
	
	
}

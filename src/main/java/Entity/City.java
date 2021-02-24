package Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * Example for both OneToMany and ManyToOne annotations with proper mappings.
 */
@Entity
public class City {

	/*
	 * If you want a Bi-directional mapping, then remove the GenerationType.Identity for this class
	 * it should not be AUTO, not SEQUENCE, not TABLE
	 * 
	 * The reason is that, this cityid is referenced as a foreign key in People table
	 * 
	 */
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cityId;

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
	 * 1. For OneToOne [City] table has a extra column called City_MobId that has FK with PK of [People] table
	 * 
	 * 2. But for OneToMany, A new table will be created -> [City_People] and it will have both City_Id and people_MobId
	 * 
	 * 3. If you want to do a Bi-directional mapping, then you have to provide a 
	 * mappedBy = "Class instance of another Entity" on a Collection for OneToMany
	 * and see another Entity 
	 * 
	 */
	
	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
	private Set<People> people = new HashSet<People>();
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
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
		return "City [cityId=" + cityId + ", names=" + names + ", area=" + area + ", population=" + population
				+ ", people=" + people + "]";
	}
	
	
	
}

package Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/*
 * To achieve ManyToMany Mapping with City to People, many cities have many people
 * 
 * People Table has mobId, City table has cityId, both are PK's per se.
 * But in this class, we are mentioning targetEntity as People class with ALL cascades
 * 
 * The Mapping table is PeopleCitymapper , with joincolumns that should be PK of targetEntity which is mobId
 * inverseJoinColumn should be the PK of the same entity class which is cityId
 * 
 */
@Entity
public class City {

	@Id
	private int cityId;

	private BigName names;
	
	private String area;
	
	private String population;

	@ManyToMany(targetEntity = People.class, cascade = {CascadeType.ALL})
	@JoinTable(name = "PeopleCityMapper", 
				joinColumns = {@JoinColumn(name="mobId")},
				inverseJoinColumns = {@JoinColumn(name ="cityId")}
				)
	
	private Set<People> people;
	
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
	
	  public Set<People> getPeople() { return people; } public void
	  setPeople(Set<People> people) { this.people = people; }
	 
	
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", names=" + names + ", area=" + area + ", population=" + population
				+ ", people=" + people + "]";
	}
	
	
	
}

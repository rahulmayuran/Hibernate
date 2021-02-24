package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/*
 * @OneToMany annotation will imply that 
 * One city has many people, from a social viewpoint
 */
@Entity
public class People {

	public People() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mobId;
	
	private int noOfLiterates;
	
	private int males;
	
	private int females;
	
	private String famousFor;
	
	/*
	 * 1.Adding a bi-directional relationship, So that City knows it has many people 
	 * and Poeple know that they belong to one City.
	 * 
	 * 2.Needs a JoinColumn that will attach the cityId from City table into People table
	 * 3.You dont have mappedBy annotation unlike OneToMany
	 */
	@ManyToOne
	@JoinColumn(name = "cityId")
	private City city;
	
	public Long getMobId() {
		return mobId;
	}
	public void setMobId(Long mobId) {
		this.mobId = mobId;
	}
	public int getNoOfLiterates() {
		return noOfLiterates;
	}
	public void setNoOfLiterates(int noOfLiterates) {
		this.noOfLiterates = noOfLiterates;
	}
	public int getMales() {
		return males;
	}
	public void setMales(int males) {
		this.males = males;
	}
	public int getFemales() {
		return females;
	}
	public void setFemales(int females) {
		this.females = females;
	}
	public String getFamousFor() {
		return famousFor;
	}
	public void setFamousFor(String famousFor) {
		this.famousFor = famousFor;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	  @Override public String toString() { return "People [mobId=" + mobId +
	  ", noOfLiterates=" + noOfLiterates + ", males=" + males + ", females=" +
	  females + ", famousFor=" + famousFor + "]"; }
	 
	
	
	
	
}

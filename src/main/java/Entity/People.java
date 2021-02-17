package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * A funny example , 1 city has 1 person. 
 * This denotes the experience of each person to his city.
 * If it is so, each person can belong to one city at a specific time 
 * unless he keeps one leg in one city and another leg in other city.
 */
@Entity
public class People {

	@Id
	private Long MobId;
	
	private int noOfLiterates;
	private int males;
	private int females;
	private String famousFor;
	public Long getMobId() {
		return MobId;
	}
	public void setMobId(Long mobId) {
		MobId = mobId;
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
	
	@Override
	public String toString() {
		return "People [MobId=" + MobId + ", noOfLiterates=" + noOfLiterates + ", males=" + males + ", females="
				+ females + ", famousFor=" + famousFor + "]";
	}
	
	
}

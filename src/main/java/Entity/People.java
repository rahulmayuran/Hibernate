package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * @OneToMany annotation will imply that 
 * One city has many people, from a social viewpoint
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

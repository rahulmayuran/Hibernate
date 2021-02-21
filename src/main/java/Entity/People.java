package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * @OneToMany annotation will imply that 
 * One city has many people, from a social viewpoint
 */
@Entity
public class People {

	public People() {
		
	}
	
	/*
	 * No need to mention the GeneratedType as Identity, You can do so,
	 * But this demo wants to map the Id which is set in Repository class, 
	 * 
	 * so that if mobId is 101, 112 and if cityId is 110, 
	 * then in mapping table we should see that 110-> 101 and 110-> 112.
	 */
	@Id
	private Long mobId;
	
	private int noOfLiterates;
	
	private int males;
	
	private int females;
	
	private String famousFor;

	/*
	 * No need to create a list of City object and map it ,
	 *  
	 * Just like you did for previous examples. If One class has the annotation 
	 * of ManyToMany, then it is enough, People doesn't need to know about City explicitly.
	 */
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
	
	@Override
	public String toString() {
		return "People [mobId=" + mobId + ", noOfLiterates=" + noOfLiterates + ", males=" + males + ", females="
				+ females + ", famousFor=" + famousFor + "]";
	}
	
	
	
	
	
}

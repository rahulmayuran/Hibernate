package Entity;

import javax.persistence.Embeddable;

@Embeddable
public class BigName {

	private String oldName;
	private String newName;
	
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	@Override
	public String toString() {
		return "BigName [oldName=" + oldName + ", newName=" + newName + "]";
	}
	
	
}

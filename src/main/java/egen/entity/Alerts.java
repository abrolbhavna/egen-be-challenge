package egen.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class Alerts {
	
	@Id private String id;
	
	private String category;
		
	private String oldWeight;

	private String currentWeight;
	
	private String timeStamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOldWeight() {
		return oldWeight;
	}

	public void setOldWeight(String oldWeight) {
		this.oldWeight = oldWeight;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(String currentWeight) {
		this.currentWeight = currentWeight;
	}		
	
}

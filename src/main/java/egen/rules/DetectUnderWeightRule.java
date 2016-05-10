package egen.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import egen.entity.Alerts;
import egen.service.AlertsTrackerService;

/*
 * Detects under weight – if the weight of the person drops below 10% of his base weight
 * Create a new alert and save it in MongoDB
*/

@Rule (name = "DetectUnderWeightRule" )
public class DetectUnderWeightRule {

	private final int percentageDrop = 10;

	private Integer currentWeight;
	private Integer baseWeight;
	public DetectUnderWeightRule(Integer currentWeight, Integer baseWeight) {
		this.currentWeight = currentWeight;
		this.baseWeight = baseWeight;
	}
	
	@Autowired
	private AlertsTrackerService alertTrackerService;
	
	@Condition
	public boolean when() {
		if(this.currentWeight < calculateDropWeight(this.baseWeight)) {
			return true;
		}
		return false;
	}
	
	@Action
	public void then() {
		System.out.println("Under weight detected creating alert");
		
		//Create Alerts Instance
		Alerts alerts = new Alerts();
		alerts.setCategory("underweight");
		alerts.setCurrentWeight(this.currentWeight.toString());
		alerts.setOldWeight(this.baseWeight.toString());
		Long timeStamp = System.currentTimeMillis();
		alerts.setTimeStamp(timeStamp.toString());
		
		alertTrackerService.createAlert(alerts);		
	}
	
	private int calculateDropWeight(int baseWeight) {
		int tenPercentOfBaseWeight = baseWeight * percentageDrop / 100 ;
		return tenPercentOfBaseWeight;
	}

}

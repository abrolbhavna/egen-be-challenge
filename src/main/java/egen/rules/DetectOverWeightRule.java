package egen.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import egen.entity.Alerts;
import egen.service.AlertsTrackerService;

/*
 * Detects over weight – if the weight of the person shoots below 10% of his base weight
 * Create a new alert and save it in MongoDB
*/

@Rule (name = "DetectOverWeightRule" )
public class DetectOverWeightRule {
	
	private Integer currentWeight;
	private Integer baseWeight;
	public DetectOverWeightRule(Integer currentWeight, Integer baseWeight) {
		this.currentWeight = currentWeight;
		this.baseWeight = baseWeight;
	}

	private final int percentageDrop = 10;
	
	@Autowired
	private AlertsTrackerService alertTrackerService;

	@Condition
	public boolean when() {
		if(this.currentWeight > calculateShootWeight(this.baseWeight)) {
			return true;
		}
		return false;
	}
	
	@Action
	public void then() {
		System.out.println("Over weight detected");
		
		//Create Alerts Instance
		Alerts alerts = new Alerts();
		alerts.setCategory("overweight");
		alerts.setCurrentWeight(this.currentWeight.toString());
		alerts.setOldWeight(this.baseWeight.toString());
		Long timeStamp = System.currentTimeMillis();
		alerts.setTimeStamp(timeStamp.toString());
		alertTrackerService.createAlert(alerts);
	}
	
	private Integer calculateShootWeight(Integer baseWeight) {
		int tenPercentOfBaseWeight = baseWeight * percentageDrop / 100 ;
		return tenPercentOfBaseWeight;
	}

}

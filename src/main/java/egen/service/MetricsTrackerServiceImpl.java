package egen.service;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egen.entity.Metrics;
import egen.rules.DetectOverWeightRule;
import egen.rules.DetectUnderWeightRule;

@Service
public class MetricsTrackerServiceImpl implements MetricsTrackerService{

	private String baseWeight = null;
	
	@Autowired
	public MetricsRepository repository;	
	
	@Autowired
	public MetricsTrackerServiceImpl(MetricsRepository repository) {
		this.repository = repository;
	}
	
	/* create metrics everytime this API is called */
	public Metrics create(String timeStamp, String value) {
		Metrics newMetrics = new Metrics();
		newMetrics.setTimeStamp(timeStamp);
		newMetrics.setValue(value);
		Metrics result = repository.save(newMetrics);
		if(baseWeight == null ) { 
			baseWeight = result.getValue();
		}
		createRuleEngine(result.getValue());
		return result;
	}

	/* read all the list of metrics from database */
	public List<Metrics> read() {
		return repository.findAll();		
	}

	/* read all the list of alerts from database and filter them by timerange*/
	public List<Metrics> readByTimeRange(String startTime, String endTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date startDate = null;
		Date endDate = null;
		Timestamp startTimeStamp = null;
		Timestamp endTimeStamp = null;
		try {
			startDate = (Date)dateFormat.parse(startTime);
			endDate = (Date)dateFormat.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(startDate != null && endDate != null) {
			startTimeStamp = new java.sql.Timestamp(startDate.getTime());	   	    
			endTimeStamp = new java.sql.Timestamp(endDate.getTime());
		}


		List<Metrics> metricsList = repository.findAll();
		List<Metrics> metricsByTimeRange = new ArrayList<Metrics>();

		for(Metrics metrics : metricsList) {
			try {
				Date metricsDate = (Date)dateFormat.parse(metrics.getTimeStamp());
				Timestamp metricsTimeStamp = new java.sql.Timestamp(metricsDate.getTime());
				if(metricsTimeStamp.after(startTimeStamp) && metricsTimeStamp.before(endTimeStamp) ) {
					metricsByTimeRange.add(metrics);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return metricsByTimeRange;
	}	

	public void createRuleEngine(String currentWeight) {
        // create a rules engine
        RulesEngine rulesEngine = aNewRulesEngine().build();
        Integer baseWeightInt = Integer.parseInt(baseWeight);
        Integer currentWeightInt = Integer.parseInt(currentWeight);
        //register the rule
        rulesEngine.registerRule(new DetectOverWeightRule(currentWeightInt, baseWeightInt));
        rulesEngine.registerRule(new DetectUnderWeightRule(currentWeightInt, baseWeightInt));
        //fire rules
        rulesEngine.fireRules();
	}
	
}

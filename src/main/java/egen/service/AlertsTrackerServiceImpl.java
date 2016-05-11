package egen.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egen.entity.Alerts;

/* ServiceImpl class that provides services to the Controller API   
 * Services includes adding new row in Alerts collection, reading all data from 
 * Alerts collection  and reading Alerts collection based on time Range
 */
@Service
public class AlertsTrackerServiceImpl implements AlertsTrackerService{

	@Autowired
	private AlertsRepository repository;	

	@Autowired
	public AlertsTrackerServiceImpl(AlertsRepository repository) {
		this.repository = repository;
	}
	
	/* create alerts when over weight or under weight rule is detected */
	public Alerts createAlert(Alerts t) {
		Alerts result = repository.save(t);
		return result;
	}
	
	/* read all the list of alerts from database */
	public List<Alerts> read() {
		return (List<Alerts>) repository.findAll();
	}

	/* read list of alerts in time range */
	public List<Alerts> readByTimeRange(String startTime, String endTime) {
		
		//assuming simple date format as "yyyy-MM-dd hh:mm:ss.SSS"
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date startDate = null;
		Date endDate = null;
		Timestamp startTimeStamp = null;
		Timestamp endTimeStamp = null;
		try {
			startDate = (Date)dateFormat.parse(startTime);
			endDate = (Date)dateFormat.parse(endTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		if(startDate != null && endDate != null) {
			startTimeStamp = new java.sql.Timestamp(startDate.getTime());	   	    
			endTimeStamp = new java.sql.Timestamp(endDate.getTime());
		}


		List<Alerts> alertsList = (List<Alerts>) repository.findAll();
		List<Alerts> alertsByTimeRange = new ArrayList<Alerts>();

		//looping through the list of alerts
		for(Alerts alerts : alertsList) {
			try {
				Date alertsDate = (Date)dateFormat.parse(alerts.getTimeStamp());
				Timestamp alertsTimeStamp = new java.sql.Timestamp(alertsDate.getTime());
				//check to find alerts between timerange
				if(alertsTimeStamp.after(startTimeStamp) && alertsTimeStamp.before(endTimeStamp) ) {
					alertsByTimeRange.add(alerts);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return alertsByTimeRange;

	}

}

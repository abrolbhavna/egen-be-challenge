package egen.service;

import java.util.List;

import egen.entity.Alerts;


public interface AlertsTrackerService {

	public List<Alerts> read();
	
	public List<Alerts> readByTimeRange(String startTimeStamp, String endTimeStamp);
	
	public Alerts createAlert(Alerts a);
}

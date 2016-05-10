package egen.service;

import java.util.List;

import egen.entity.Metrics;


public interface MetricsTrackerService {
	public Metrics create(String timeStamp, String value);
	
	public List<Metrics> read();
	
	public List<Metrics> readByTimeRange(String startTimeStamp, String endTimeStamp);


}


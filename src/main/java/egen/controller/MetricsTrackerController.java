package egen.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egen.entity.Metrics;
import egen.service.MetricsTrackerService;

/* Metrics API to save and read metrics in database */

@RestController
@RequestMapping(value="/track/metrics")
public class MetricsTrackerController  {

	@Autowired 
	private MetricsTrackerService metricsTrackerService;

	/*
	 * this is the API that will consume data from the sensor emulator and save in metrics database
	 */
	
	@RequestMapping(value = "/metrics", method = RequestMethod.POST, params = "application/json")
	public Metrics create(@RequestParam(value="timeStamp") String timeStamp, @RequestParam (value="value") String value){
		Metrics result = metricsTrackerService.create(timeStamp, value);		
		return result;
	}

	/*
	 * reads all metrics that are stored in the database
	 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Iterable<Metrics> read() {
		return metricsTrackerService.read();
	}

	/*
	 * reads all alerts that are created between the given two timestamps
	 */
	@RequestMapping(value = "/readByTimeRange", method = RequestMethod.GET)
	public List<Metrics> readByTimeRange(@PathVariable String startTime, @PathVariable String endTime) throws ParseException {
		return metricsTrackerService.readByTimeRange(startTime, endTime);
	}


}
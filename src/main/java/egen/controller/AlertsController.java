package egen.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egen.entity.Alerts;
import egen.service.AlertsTrackerService;

/* Alerts API to read alerts stored in database */

@RestController
@RequestMapping(value="/track/alerts")
public class AlertsController  {

	@Autowired 
	private AlertsTrackerService alertTrackerService;

	/*
	 * reads all alerts that are stored in the database
	 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Iterable<Alerts> read() {
		return alertTrackerService.read();
	}

	/*
	 * reads all alerts that are created between the given two timestamps
	 */
	@RequestMapping(value = "/readByTimeRange", method = RequestMethod.GET)
	public List<Alerts> readByTimeRange(@PathVariable String startTime, @PathVariable String endTime) throws ParseException {
		return alertTrackerService.readByTimeRange(startTime, endTime);
	}


}
package egen.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egen.entity.Alerts;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/application-context.xml" })
public class AlertsRepositoryTest {
	
	

	@Autowired
	protected AlertsTrackerService alertTrackerService;
	@Autowired
	protected AlertsRepository alertsRepository;	

	@Autowired 
	private MongoOperations mongoOps;

	@Before
	public void init() {
		//alertsRepository.deleteAll();
		TestData.alerts();
	}    


	@Test
	public void testcreateAlert() {

		//get mock data to test
		List<Alerts> alertsList = getMockData();

		alertTrackerService.createAlert(alertsList.get(0));	
		Assert.assertEquals(alertsRepository.count(), 1);

	}

	public List<Alerts> getMockData() {
		//Prepare Mock test data
		List<Alerts> alertsList = new ArrayList<Alerts>();
		Alerts a1 = new Alerts();
		a1.setCategory("overWeight");
		a1.setCurrentWeight("150");
		a1.setOldWeight("120");
		a1.setTimeStamp(((Long)System.currentTimeMillis()).toString());

		Alerts a2 = new Alerts();
		a2.setCategory("underWeight");
		a2.setCurrentWeight("120");
		a2.setOldWeight("150");
		a2.setTimeStamp("1458062848734");

		alertsList.add(a1);
		alertsList.add(a2);

		return alertsList;


	}
}

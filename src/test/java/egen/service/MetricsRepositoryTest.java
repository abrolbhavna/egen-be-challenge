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
public class MetricsRepositoryTest {
	
	

	@Autowired
	protected MetricsTrackerService metricsTrackerService;
	@Autowired
	protected MetricsRepository metricsRepository;	

	@Autowired 
	private MongoOperations mongoOps;

	@Before
	public void init() {
		TestData.metrics();
	}    


	@Test
	public void testcreateMetrics() {
		metricsTrackerService.create("1458062848734", "150");
		
		long count =  metricsRepository.count();
		long actual = 1;
		
		Assert.assertEquals(count, actual);
		
	}
}

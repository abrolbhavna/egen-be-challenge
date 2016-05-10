package egen.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import egen.entity.Metrics;
import egen.utility.MongoUtil;

public class MetricsDaoTest {
	private final static Logger logger = LoggerFactory
			.getLogger(MetricsDaoTest.class);

	private Mongo mongo;
	private Morphia morphia;
	private MetricsDao metricsDao;
	private final String dbname = "peopledb";

	@Before
	public void initiate() {
		mongo = MongoUtil.getMongo();
		morphia = new Morphia();
		morphia.map(Metrics.class);
		metricsDao = new MetricsDao(mongo, morphia, dbname);
	}

	@Test
	public void test() {
		long counter = metricsDao.count();
		logger.debug("The count is [" + counter + "]");

		Metrics t = new Metrics();
		t.setTimeStamp("1458062848734");
		t.setValue("153");
		metricsDao.save(t);

		long newCounter = metricsDao.count();
		logger.debug("The new count is [" + newCounter + "]");
		System.out.println(t.getValue());
		assertTrue((counter + 1) == newCounter);


	}
}
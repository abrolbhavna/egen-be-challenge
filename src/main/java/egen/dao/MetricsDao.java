package egen.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.emul.org.bson.types.ObjectId;
import com.mongodb.Mongo;

import egen.entity.Metrics;

public class MetricsDao extends BasicDAO<Metrics, ObjectId> {

	public MetricsDao(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}	
	
	
}

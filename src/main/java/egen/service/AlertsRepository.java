package egen.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import egen.entity.Alerts;

public interface AlertsRepository extends
MongoRepository<Alerts, String> {

}

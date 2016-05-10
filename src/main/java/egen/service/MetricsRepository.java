package egen.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import egen.entity.Metrics;

@Configuration
@ComponentScan
public interface MetricsRepository extends
MongoRepository<Metrics, String> {

}


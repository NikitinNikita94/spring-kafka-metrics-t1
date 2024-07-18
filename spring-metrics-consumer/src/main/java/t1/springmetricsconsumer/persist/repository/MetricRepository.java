package t1.springmetricsconsumer.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1.springmetricsconsumer.persist.entity.Metric;

import java.util.UUID;

public interface MetricRepository extends JpaRepository<Metric, UUID> {
}

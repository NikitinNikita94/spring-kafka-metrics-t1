package t1.springmetricsconsumer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import t1.springmetricsconsumer.persist.ReadMetricsDto;
import t1.springmetricsconsumer.service.MetricService;


@Slf4j
@Component
@EnableKafka
@RequiredArgsConstructor
public class Consumer {

    private final MetricService metricService;

    /**
     * Метод слушает определенный топик и сохраняет метрики в бд.
     *
     * @param message - сообщение с метрикой
     */
    @Transactional
    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2))
    @KafkaListener(id = "metricsId", topics = "metrics-topic")
    public void listenMetricTopic(final String message) {
        log.info("I received a message with contents: {}", message);

        metricService.saveMetrics(new ReadMetricsDto(message));

        log.info("The message with the metric was sent to the service");
    }
}

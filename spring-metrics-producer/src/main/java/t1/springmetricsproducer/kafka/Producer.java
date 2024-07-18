package t1.springmetricsproducer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", matchIfMissing = false)
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendEvent(final String topic, final String event) throws JsonProcessingException {
        Assert.hasText(topic, "topic must not be blank");
        Assert.notNull(event, "KafkaEvent must not be null");

        kafkaTemplate.send(topic, event);
    }
}

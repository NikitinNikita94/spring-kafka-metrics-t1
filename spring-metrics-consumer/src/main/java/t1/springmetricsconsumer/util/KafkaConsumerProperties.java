package t1.springmetricsconsumer.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka")
public record KafkaConsumerProperties(String bootstrapAddress, String topic, String groupId) {
}

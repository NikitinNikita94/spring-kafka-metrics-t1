package t1.springmetricsproducer.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app.producer")
public record KafkaProducerProperties(String host, List<String> methods, String topic, String key) {
}

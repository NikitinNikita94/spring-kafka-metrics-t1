package t1.springmetricsproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import t1.springmetricsproducer.integration.service.WebClientService;
import t1.springmetricsproducer.kafka.Producer;
import t1.springmetricsproducer.util.KafkaProducerProperties;


@Service
@RequiredArgsConstructor
public class KafkaServiceProducer {

    private final Producer kafkaProducer;
    private final WebClientService webClientService;
    private final KafkaProducerProperties properties;

    /**
     * Метод отправляет сообщение(ивент) в указанный топик.
     *
     * @param event - сообщение.
     */
    @SneakyThrows
    public void sendEvent(final String event) {
        kafkaProducer.sendEvent(properties.topic(),
                webClientService
                        .getActuatorMessage(event));
    }
}

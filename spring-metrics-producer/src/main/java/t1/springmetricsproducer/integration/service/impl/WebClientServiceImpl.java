package t1.springmetricsproducer.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import t1.springmetricsproducer.integration.service.WebClientService;
import t1.springmetricsproducer.util.KafkaProducerProperties;

import java.time.Duration;

@Service
@Primary
@RequiredArgsConstructor
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;
    private final KafkaProducerProperties properties;

    @Override
    public String getActuatorMessage(final String actuatorName) {
        return webClient
                .get()
                .uri(properties.methods().get(1) + actuatorName)
                .retrieve()
                .bodyToFlux(String.class)
                .onErrorResume(err -> Flux.empty())
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(2)))
                .blockFirst();
    }
}

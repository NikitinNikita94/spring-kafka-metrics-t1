package t1.springmetricsproducer.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import t1.springmetricsproducer.service.KafkaServiceProducer;

@RestController
@RequestMapping("/api/v1/producer-metrics")
@RequiredArgsConstructor
@Tag(name = "Поставщик метрик приложения", description = "Эндпоинты для работы по созданию метрик.")
public class KafkaProducerMetricsController {

    private final KafkaServiceProducer kafkaServiceProducer;

    @PostMapping
    @Operation(
            summary = "Создать новую метрику",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Запрос выполнен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public void sendEvent(@RequestParam(name = "metricName") final String metricName) {
        kafkaServiceProducer.sendEvent(metricName);
    }
}

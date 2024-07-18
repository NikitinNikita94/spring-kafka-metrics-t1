package t1.springmetricsconsumer.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.springmetricsconsumer.persist.ReadMetricsDto;
import t1.springmetricsconsumer.service.MetricService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
@Tag(name = "Метрики", description = "Эндпоинты для работы с метриками")
public class MetricsRestController {

    private final MetricService metricService;

    @GetMapping
    @Operation(
            summary = "Получить список всех метрик",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Запрос выполнен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ReadMetricsDto.class))
                            )
                    )
            }
    )
    public List<ReadMetricsDto> getAllMetrics(@PageableDefault(size = 40, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return metricService.getAllMetrics(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить метрику по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Запрос выполнен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ReadMetricsDto.class))
                            )
                    )
            }
    )
    public ReadMetricsDto getMetric(@PathVariable UUID id) {
        return metricService.getMetricsById(id);
    }
}

package t1.springmetricsconsumer.kafka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import t1.springmetricsconsumer.persist.ReadMetricsDto;
import t1.springmetricsconsumer.service.MetricService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты Consumer")
class ConsumerTest {

    @Mock
    private MetricService metricService;

    @InjectMocks
    private Consumer consumer;

    @Test
    void listenMetricTopic_ShouldVerifyMessage() {
        String kafkaMessage = "test_message";
        ReadMetricsDto readMetricsDto = new ReadMetricsDto(kafkaMessage);

        doNothing().when(metricService).saveMetrics(readMetricsDto);

        consumer.listenMetricTopic(kafkaMessage);

        verify(metricService, times(1)).saveMetrics(readMetricsDto);
    }
}
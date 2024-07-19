package t1.springmetricsproducer.kafka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты Producer")
public class ProducerTest {

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @InjectMocks
    private Producer producer;

    @Test
    @DisplayName("Проверка метода SendEvent() при успешном выполнении")
    public void testSendEvent_whenMetricExist_sendMetric() throws Exception {
        String metricName = "endpoint";
        String topic = "topic";

        when(kafkaTemplate.send(anyString(), anyString())).thenReturn(null);

        producer.sendEvent(topic, metricName);
        verify(kafkaTemplate, times(1)).send(anyString(), anyString());
    }
}
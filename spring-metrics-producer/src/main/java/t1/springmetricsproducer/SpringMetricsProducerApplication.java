package t1.springmetricsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import t1.springmetricsproducer.util.KafkaProducerProperties;

@SpringBootApplication
@EnableConfigurationProperties(KafkaProducerProperties.class)
@EnableDiscoveryClient
public class SpringMetricsProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMetricsProducerApplication.class, args);
    }

}

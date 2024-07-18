package t1.springmetricsconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import t1.springmetricsconsumer.util.KafkaConsumerProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(KafkaConsumerProperties.class)
public class SpringMetricsConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMetricsConsumerApplication.class, args);
    }

}

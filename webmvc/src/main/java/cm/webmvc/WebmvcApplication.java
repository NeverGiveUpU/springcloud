package cm.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableZuulProxy
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class WebmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebmvcApplication.class, args);
    }


    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }
//    public RestTemplate rest(RestTemplateBuilder builder) {
//        return builder.build();
//    }

}

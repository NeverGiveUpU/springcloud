package cm.webmvc;

import cm.webmvc.loadbalance.test.TestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;


@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = TestConfiguration.class))
@EnableAspectJAutoProxy
//@EnableZuulProxy
@EnableFeignClients
//@EnableCircuitBreaker
//@EnableDiscoveryClient
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

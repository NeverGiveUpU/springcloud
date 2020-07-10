package cm.webmvc.loadbalance.test;

import feign.Feign;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 陈濛
 * @date 2020/7/10 1:33 上午
 */
@Configuration
public class TestConfiguration {
    // 在配置文件里加
//    @Bean
//    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
//                              SpringClientFactory clientFactory,
//                              @Autowired(required = false) DiscoveryClient discoveryClient) {
//
//        return new PrivateLoadBalancerFeignClient(new Client.Default(null, null),
//                cachingFactory, clientFactory, discoveryClient);
//    }

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(Retryer retryer) {
        return MyFeign.builder().retryer(retryer);
    }
}

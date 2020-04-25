package cm.reactive.gateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈濛
 * @date 2020/4/22 2:51 下午
 */
@EnableConfigurationProperties(UriConfiguration.class)
@Configuration
public class GatewayConfig {

    /**
     * 路由过滤、修改和转发规则。
     * /get，添加Header，并转发到http://httpbin.org:80
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {

        String httpUri = uriConfiguration.getHttpbin();

        return builder.routes()
                //路由规则1：/get，添加Header，并转发到指定路由
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                //路由规则2：主机名为.hystrix.com后缀，用hystrix添加熔断，并转发到指定路由。
                //如果转发路由超时，则触发熔断，转发到本服务的指定路由。
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f.hystrix(config -> config
                                .setName("mycmd")
                                .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
    }
}

package cm.reactive.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author 陈濛
 * @date 2020/4/23 12:23 上午
 */
@RestController
public class GatewayController {
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("转发路由失败");
    }
}

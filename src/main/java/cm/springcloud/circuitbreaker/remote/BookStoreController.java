package cm.springcloud.circuitbreaker.remote;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author 陈濛
 * @date 2020/4/23 5:14 下午
 *
 * 模拟外部服务，在这里ip和端口和调用端保持一致，实际上是不一样的。
 */
@RestController
public class BookStoreController {
    @RequestMapping(value = "/remote/recommended")
    public Mono<String> readingList(@RequestParam String name){
        if (name.equals("1")) {
            throw new RuntimeException("书店服务错误");
        }
        return Mono.just("Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)");
    }

}

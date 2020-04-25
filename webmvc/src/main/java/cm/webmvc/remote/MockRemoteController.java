package cm.webmvc.remote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author 陈濛
 * @date 2020/4/23 5:14 下午
 *
 * 模拟外部服务，在这里ip和端口和调用端保持一致，实际上是不一样的。
 */
@Slf4j
@RestController
public class MockRemoteController {

    @RequestMapping(value = "/remote/recommended")
    public Mono<String> readingList(@RequestParam String name){
        if (name.equals("1")) {
            throw new RuntimeException("书店服务错误");
        }
        return Mono.just("Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)");
    }


    @GetMapping("/remote/greeting")
    public String greet() {
        log.info("Access /remote/greeting");

        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
        Random rand = new Random();

        int randomNum = rand.nextInt(greetings.size());
        return greetings.get(randomNum);
    }

    @GetMapping("/remote")
    public String home() {
        log.info("Access /remote");
        return "Hi!";
    }

    @RequestMapping(value = "/available")
    public String available() {
        return "Spring in Action";
    }

    @RequestMapping(value = "/checked-out")
    public String checkedOut() {
        return "Spring Boot in Action";
    }
}

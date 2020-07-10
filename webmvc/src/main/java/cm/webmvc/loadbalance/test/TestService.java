package cm.webmvc.loadbalance.test;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author 陈濛
 * @date 2020/7/9 11:10 下午
 */
@Component
public class TestService {
    public String get() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 0)
            return "localhost:8080/remote";
        else
            return "localhost:8080/fuck";
    }
}

package cm.webmvc.loadbalance;
import cm.webmvc.loadbalance.test.TestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈濛
 * @date 2020/4/25 2:33 下午
 *
 * 通过Feign访问外部服务，实现客户端负载均衡
 */
//@FeignClient(value = "webmvc")
@FeignClient(value = "fuck", url = "hello", configuration = TestConfiguration.class)
public interface FeignClientInterface {

    @GetMapping("greeting")
    String hi(@RequestParam(value="name", defaultValue="Artaban") String name);
}

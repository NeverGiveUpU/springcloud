package cm.webmvc.loadbalance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈濛
 * @date 2020/4/25 2:00 上午
 */
@Slf4j
@RestController
public class ClientLoadBalanceController {

    @Autowired
    FeignClientInterface feignClient;

    @GetMapping("/hi")
    public String hi(@RequestParam(value="name", defaultValue="Artaban") String name) {
        return feignClient.hi(name);
    }
}

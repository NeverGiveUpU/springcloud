package cm.webmvc.loadbalance.test;

import cm.webmvc.loadbalance.FeignClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈濛
 * @date 2020/7/9 11:31 下午
 */
@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    FeignClientInterface feignClientInterface;

    @GetMapping("test")
    public String get(@RequestParam("name") String name) {
        String re = feignClientInterface.hi(name);
        return re;
    }
}

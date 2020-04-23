package cm.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String allService() {
        String service = "Service" + discoveryClient.getServices();
        System.out.println(service);
        return service;
    }
}

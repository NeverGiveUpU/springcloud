package cm.webmvc.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 所有应用
     * @return
     */
    @GetMapping("/discovery/all")
    public String allService() {
        String service = "Service" + discoveryClient.getServices();
        System.out.println(service);
        return service;
    }

    /**
     * 应用的所有实例
     * @param applicationName 应用名
     * @return
     */
    @GetMapping("/discovery/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {

        return this.discoveryClient.getInstances(applicationName);
    }
}

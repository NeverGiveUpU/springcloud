package cm.webmvc.loadbalance.test;

import com.netflix.client.config.IClientConfig;
import feign.Client;
import feign.Request;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrivateLoadBalancerFeignClient extends LoadBalancerFeignClient {

    private static final Logger log = LoggerFactory.getLogger(PrivateLoadBalancerFeignClient.class);

    private DiscoveryClient discoveryClient;
    private Client delegate;
    private CachingSpringLoadBalancerFactory lbClientFactory;
    private SpringClientFactory clientFactory;

    @Autowired
    private TestService testService;

    public PrivateLoadBalancerFeignClient(Client delegate,
                                          CachingSpringLoadBalancerFactory lbClientFactory,
                                          SpringClientFactory clientFactory, DiscoveryClient discoveryClient) {
        super(delegate, lbClientFactory, clientFactory);
        this.delegate = delegate;
        this.lbClientFactory = lbClientFactory;
        this.clientFactory = clientFactory;

        this.discoveryClient = discoveryClient;
    }

    /**
     *
     * @param request
     * @param options
     * @return
     * @throws IOException
     */
    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        String url = request.url();
        URI uri = URI.create(url);
        String clientName = uri.getHost();
        Map<String, Collection<String>> headers = new HashMap<>(request.headers());

        // 从全局中获取该服务名称的配置信息
        IClientConfig clientConfig = this.clientFactory.getClientConfig(clientName);
        url = testService.get();

        //重新构建 request　对象
        Request newRequest = Request.create(request.method(),
                url, headers, request.body(),
                request.charset());
        return super.execute(newRequest, options);

//        if (null != clientConfig) {
//
//            url = testService.get();
//
//            //重新构建 request　对象
//            Request newRequest = Request.create(request.method(),
//                    url, headers, request.body(),
//                    request.charset());
//            return super.execute(newRequest, options);
//        }else{
//            return super.execute(request, options);
//        }
    }




}
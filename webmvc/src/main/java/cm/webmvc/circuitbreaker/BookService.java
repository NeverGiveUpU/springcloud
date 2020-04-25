package cm.webmvc.circuitbreaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BookService {

    private final RestTemplate restTemplate;

    //注入
    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList(String name) {
//        URI uri = URI.create("http://localhost:8080/remote/recommended");

        String uri = "http://localhost:8080/remote/recommended?name={name}";
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        return this.restTemplate.getForObject(uri, String.class, params);
    }

    public String reliable(String name) {
        return "Cloud Native Java (O'Reilly)";
    }
}
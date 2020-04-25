package cm.reactive.circuitbreaker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BookService {

  private final WebClient webClient;
  private final ReactiveCircuitBreaker readingListCircuitBreaker;

  public BookService(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
    this.webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    this.readingListCircuitBreaker = circuitBreakerFactory.create("recommended");
  }

  /**
   * 将调用外部服务的代码 和 服务降级（fallback）的代码 封装到 熔断器对象中。
   * 熔断器负责监控。
   * @return
   */
  public Mono<String> readingList(String name) {
    return readingListCircuitBreaker.run(
            webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .path("/remote/recommended")
                            .queryParam("name", name)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class),
            throwable -> {
              log.warn("Error making request to book service", throwable);
              return Mono.just("Cloud Native Java (O'Reilly)");
            });
  }
}
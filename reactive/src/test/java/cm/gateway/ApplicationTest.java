package cm.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;


/**
 * 利用Spring Cloud Contract中的WireMock可以模拟外部路由的服务器。
 * 首先要注意的是的使用@AutoConfigureWireMock(port = 0)。此注释将为我们在随机端口上启动WireMock。
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"httpbin=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
public class ApplicationTest {

  //模拟请求的客户端
  @Autowired
  private WebTestClient webClient;

  @Test
  public void contextLoads() throws Exception {
    //Stubs
    //模拟外部路由服务
    stubFor(get(urlEqualTo("/get"))
        .willReturn(aResponse()
                .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                .withHeader("Content-Type", "application/json")));

    stubFor(get(urlEqualTo("/delay/3"))
      .willReturn(aResponse()
              .withBody("no fallback")
              .withFixedDelay(3000))); //3秒后再response

    webClient
      .get().uri("/get")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .jsonPath("$.headers.Hello").isEqualTo("World");

    webClient
      .get().uri("/delay/3")
      .header("Host", "www.hystrix.com")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .consumeWith(
        response -> assertThat(response.getResponseBody()).isEqualTo("转发路由失败".getBytes()));
  }
}
package cm.webmvc.loadbalance.test;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 陈濛
 * @date 2020/7/10 7:47 上午
 */
@Slf4j
public class MyFeign {
    public static Builder builder() {
        log.info("自定义feign bulder");
        return new Builder();
    }

    public static final class Builder extends Feign.Builder {
        @Override
        public <T> T target(Class<T> apiType, String url) {
            return target(new MyTarget<>(apiType, url));
        }
    }
}

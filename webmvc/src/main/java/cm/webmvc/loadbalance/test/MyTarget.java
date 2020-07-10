package cm.webmvc.loadbalance.test;

import feign.Request;
import feign.RequestTemplate;
import feign.Target;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 陈濛
 * @date 2020/7/10 1:47 上午
 */
@Slf4j
public class MyTarget<T> extends Target.HardCodedTarget<T> {

    TestUtil testUtil = new TestUtil();

    public MyTarget(Class<T> type, String url) {
        super(type, url);
    }

    public MyTarget(Class<T> type, String name, String url) {
        super(type, name, url);
    }

    @Override
    public String url() {
        log.info("aaaaaaaa");
        return testUtil.get();
    }

    @Override
    public Request apply(RequestTemplate input) {
        if (input.url().indexOf("http") != 0) {
            input.target(url());
        }
        return input.request();
    }
}

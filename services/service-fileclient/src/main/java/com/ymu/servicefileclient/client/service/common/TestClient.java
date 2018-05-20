package com.ymu.servicefileclient.client.service.common;

import com.ymu.servicecommon.vo.req.VTestReq;
import com.ymu.servicecommon.vo.resp.VTestResp;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-common",fallbackFactory = TestClientFallbackFactory.class)
public interface TestClient {

    @PostMapping("/test/test")
    VTestResp test(@RequestBody @Validated VTestReq vTestReq);

    @GetMapping(value = "/test/test2")
    String test2(@RequestParam(value = "name") String name);

    @GetMapping(value = "/test/test2",headers = {"Content-Version=1"})
    Boolean test22(@RequestParam(value = "name") String name);

}

@Component
class TestClientFallbackFactory implements FallbackFactory<TestClient> {


    @Override
    public TestClient create(Throwable cause) {

        return new TestClient() {
            @Override
            public VTestResp test(VTestReq vTestReq) {
                return new VTestResp();
            }

            @Override
            public String test2(String name) {
                return "test2" + cause.getMessage();
            }

            @Override
            public Boolean test22(String name) {
                return false;
            }
        };
    }
}


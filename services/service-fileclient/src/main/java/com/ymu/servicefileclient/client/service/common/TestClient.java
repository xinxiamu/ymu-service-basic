package com.ymu.servicefileclient.client.service.common;

import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.servicecommon.vo.req.VTestReq;
import com.ymu.servicecommon.vo.resp.VTestResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-common",fallback = TestClient.TestClientFallback.class)
public interface TestClient {

    @PostMapping("/test/test")
    ApiResult<VTestResp> test(@RequestBody @Validated VTestReq vTestReq);

    @GetMapping(value = "/test/test222")
    ApiResult<String> test2(@RequestParam(value = "name") String name);

    @GetMapping(value = "/test/test2",headers = {"Content-Version=1"})
    ApiResult<String> test22(@RequestParam(value = "name") String name);

    @Component
    class TestClientFallback implements TestClient {

        @Override
        public ApiResult<VTestResp> test(VTestReq vTestReq) {

            return new ApiResult<>();
        }

        @Override
        public ApiResult<String> test2(String name) {
            return new ApiResult<>("abc");
        }

        @Override
        public ApiResult<String> test22(String name) {
            return null;
        }
    }
}

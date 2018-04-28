package com.ymu.servicefileclient.client.service.common;

import com.ymu.framework.spring.mvc.api.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-common")
public interface TestClient {

    @GetMapping(value = "/test/test2")
    ApiResult<String> test2(@RequestParam(value = "name") String name);

    @GetMapping(value = "/test/test2",headers = {"Content-Version=1"})
    ApiResult<String> test22(@RequestParam(value = "name") String name);
}

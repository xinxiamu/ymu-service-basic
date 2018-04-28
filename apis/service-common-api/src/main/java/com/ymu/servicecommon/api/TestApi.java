package com.ymu.servicecommon.api;

import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.framework.spring.mvc.api.ApiVersion;
import com.ymu.framework.spring.mvc.sensitive.SensitiveFormat;
import com.ymu.servicecommon.vo.req.VTestReq;
import com.ymu.servicecommon.vo.resp.VTestResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs11.wrapper.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能简述:<br>
 *  测试demo。注意，有的请求参数必须定义vo，返回值也定义vo并继承VBase
 *
 * @author zmt
 * @create 2018-03-07 下午2:47
 * @updateTime
 * @since 1.0.0
 */
@RequestMapping("/test")
public interface TestApi {

    @PostMapping
    ApiResult<VTestResp> test(@RequestBody @Validated VTestReq vTestReq);

    @GetMapping("${path.test.test2}")
//    @GetMapping("/test2")
    ApiResult<String> test2(@RequestParam(value = "name")  String name);

    @GetMapping("${path.test.test2}")
    @ApiVersion(1)//api版本
    ApiResult<Boolean> test22(@RequestParam(value = "name") String name);

    @GetMapping("${path.test.test3}")
    ApiResult<VTestResp> test3(String name);

    @GetMapping("${path.test.test3}")
    @ApiVersion(2)//api版本
    ApiResult<VTestResp> test33(String name);

    @GetMapping("${path.test.test3}")
    @ApiVersion(3)//api版本
    ApiResult<List<VTestResp>> test34(String name);

//    @GetMapping("${path.test.test3}")
//    @ApiVersion(3)//api版本
//    PageMo test35(String name);
}

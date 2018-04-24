package com.ymu.servicecommon.api;

import com.ymu.framework.spring.mvc.api.ApiVersion;
import com.ymu.servicecommon.vo.req.VTestReq;
import com.ymu.servicecommon.vo.resp.VTestResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    VTestResp test(VTestReq vTestReq);

    //请求无效,请求参数，响应参数必须是继承VBase的bean
    @GetMapping("/test2")
    String test2(String name);

    @GetMapping("/test3")
    VTestResp test3(String name);

    @GetMapping("/test3")
    @ApiVersion(2)//api版本
    VTestResp test33(String name);
}

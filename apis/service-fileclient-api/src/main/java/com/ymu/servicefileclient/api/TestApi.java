package com.ymu.servicefileclient.api;

import com.ymu.servicefileclient.vo.req.VTestReq;
import com.ymu.servicefileclient.vo.resp.VTestResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述:<br>
 *  测试demo。注意，说有的请求参数必须定义vo，返回值也定义vo并继承VBase
 *
 * @author zmt
 * @create 2018-03-07 下午2:47
 * @updateTime
 * @since 1.0.0
 */
@RequestMapping("${api.path.test.root}")
public interface TestApi {

    @PostMapping
    VTestResp test(@RequestBody VTestReq vTestReq);

    //请求无效,请求参数，响应参数必须是继承VBase的bean
    @GetMapping("/test2")
    String test2(String name);
}

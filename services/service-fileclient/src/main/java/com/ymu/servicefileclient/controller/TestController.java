package com.ymu.servicefileclient.controller;

import com.ymu.framework.base.AbstractController;
import com.ymu.framework.spring.mvc.sensitive.SensitiveFormat;
import com.ymu.servicefileclient.api.TestApi;
import com.ymu.servicefileclient.vo.req.VTestReq;
import com.ymu.servicefileclient.vo.req.VTestReqValidator;
import com.ymu.servicefileclient.vo.resp.VTestResp;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述:<br>
 *
 * @author zmt
 * @create 2018-03-07 下午2:49
 * @updateTime
 * @since 1.0.0
 */
@RestController
@RefreshScope
public class TestController extends AbstractController implements TestApi {

    @Override
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new VTestReqValidator());
    }

    @Override
    public VTestResp test(@RequestBody @SensitiveFormat @Validated VTestReq vTestReq) {
        VTestResp testResp = new VTestResp();
        testResp.setName("张木天");
        testResp.add(new Link("baidu.com").withRel("baidu"));
        return testResp;
    }

    @Override
    public String test2(@SensitiveFormat String name) {
        return name;
    }

    @Override
    public VTestResp test3(@SensitiveFormat String name) {
        VTestResp testResp = new VTestResp();
        testResp.setName(name);
        return testResp;
    }

    @Override
    public VTestResp test33(String name) {
        VTestResp testResp = new VTestResp();
        testResp.setName(name + "v2");
        return testResp;
    }
}

package com.ymu.service.fileclient.controller;

import com.abs.infrastructure.base.BaseController;
import com.abs.infrastructure.spring.mvc.sensitive.SensitiveFormat;
import com.ymu.service.fileclient.api.TestApi;
import com.ymu.service.fileclient.vo.req.VTestReq;
import com.ymu.service.fileclient.vo.req.VTestReqValidator;
import com.ymu.service.fileclient.vo.resp.VTestResp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class TestController extends BaseController implements TestApi {

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
}

package com.ymu.servicecommon.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.framework.spring.mvc.api.APIs;
import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.framework.spring.mvc.sensitive.SensitiveFormat;
import com.ymu.servicecommon.api.TestApi;
import com.ymu.servicecommon.vo.req.VTestReq;
import com.ymu.servicecommon.vo.req.VTestReqValidator;
import com.ymu.servicecommon.vo.resp.VTestResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * 功能简述:<br>
 *     测试类。
 *
 * @author zmt
 * @create 2018-03-07 下午2:49
 * @updateTime
 * @since 1.0.0
 */
@RestController
@RefreshScope
public class TestController extends BaseController implements TestApi {

    @Value("${api.password}")
    private String apiPwd;

    @Override
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new VTestReqValidator());
    }

    @Override
    public VTestResp test(VTestReq vTestReq) {
        VTestResp testResp = new VTestResp();
        testResp.setName("张木天");
        testResp.add(new Link("baidu.com").withRel("baidu"));

        return testResp;
    }

    @Override
    public String test2(@SensitiveFormat String name) {
//        int a = 9/0;
        HttpServletRequest r = getRequest();
        String version = r.getHeader("Content-Version");
        return name + version + ">>>";
    }

    @Override
    public Boolean test22(@SensitiveFormat String name) {
        logger.debug(">>>>name=" + name);
        if ("zmt".equals(name)) {
            return true;
        } else {
//            int a = 1/0;
            throw APIs.error(400,"错误",null);
//            return false;
        }
    }


    @Override
    public VTestResp test3(@SensitiveFormat String name) {
        logger.debug(">>>>name=" + name);
        VTestResp testResp = new VTestResp();
        testResp.setName(name + ">>>>" + apiPwd + getRequest().getHeader("Content-Version"));
        testResp.setSex(true);
//        testResp.setFlg(false);

        return testResp;
    }

    @Override
    public VTestResp test33(String name) {
        VTestResp testResp = new VTestResp();
        testResp.setName(name + "v2");

        return testResp;
    }

    @Override
    public List<VTestResp> test34(String name) {
        List<VTestResp> list = Collections.emptyList();
        for (int i = 0; i < 3; i++) {
            VTestResp testResp = new VTestResp();
            testResp.setName(name + i + ">>>>" + apiPwd + getRequest().getHeader("Content-Version"));
            list.add(testResp);
        }
        return list;
    }
}

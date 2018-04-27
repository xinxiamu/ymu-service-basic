package com.ymu.servicecommon.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.framework.spring.mvc.sensitive.SensitiveFormat;
import com.ymu.servicecommon.api.TestApi;
import com.ymu.servicecommon.vo.req.VTestReq;
import com.ymu.servicecommon.vo.req.VTestReqValidator;
import com.ymu.servicecommon.vo.resp.VTestResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ApiResult<VTestResp> test(@RequestBody @Validated VTestReq vTestReq) {
        VTestResp testResp = new VTestResp();
        testResp.setName("张木天");
        testResp.add(new Link("baidu.com").withRel("baidu"));

        ApiResult<VTestResp> apiResult = new ApiResult<>();
        apiResult.setData(testResp);
        return apiResult;
    }

    @Override
    public ApiResult<String> test2(@SensitiveFormat String name) {
        logger.debug(">>>add header:" + getRequest().getHeader("abc"));
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setData(name + getRequest().getHeader("abc"));
        return apiResult;
    }

    @Override
    public ApiResult<Boolean> test22(String name) {
        logger.debug(">>>>name=" + name);
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if ("zmt".equals(name)) {
            apiResult.setData(true);
        } else {
            apiResult.setData(false);
        }
        return apiResult;
    }


    @Override
    public ApiResult<VTestResp> test3(@SensitiveFormat String name) {
        logger.debug(">>>>name=" + name);
        VTestResp testResp = new VTestResp();
        testResp.setName(name + ">>>>" + apiPwd + getRequest().getHeader("Content-Version"));
        testResp.setSex(true);
//        testResp.setFlg(false);

        ApiResult<VTestResp> apiResult = new ApiResult<>();
        apiResult.setData(testResp);
        return apiResult;
    }

    @Override
    public ApiResult<VTestResp> test33(String name) {
        VTestResp testResp = new VTestResp();
        testResp.setName(name + "v2");

        ApiResult<VTestResp> apiResult = new ApiResult<>();
        apiResult.setData(testResp);
        return apiResult;
    }

    @Override
    public ApiResult<List<VTestResp>> test34(String name) {
        return null;
    }
}

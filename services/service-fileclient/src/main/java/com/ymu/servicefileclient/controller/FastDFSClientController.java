package com.ymu.servicefileclient.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.servicefileclient.api.FastDFSClientApi;
import com.ymu.servicefileclient.client.service.common.TestClient;
import com.ymu.servicefileclient.service.IFastDFSClientService;
import com.ymu.servicefileclient.vo.resp.VFileResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FastDFSClientController extends BaseController implements FastDFSClientApi {

    @Autowired
    private IFastDFSClientService fastDFSClientService;

    @Resource
    private TestClient testClient;

    @Override
    public ApiResult<List<VFileResp>> getAllFile() {
        //调用common服务
        String result = testClient.test2("abc");
        logger.debug(">>>消费common组件：" + result);

        VFileResp vFileResp = new VFileResp();
        vFileResp.setName(result);
        vFileResp.setUrl("http://baidu.com/a.png");
        vFileResp.add(new Link("google.com").withSelfRel());
        vFileResp.add(new Link("ymu.com").withRel("ym"));

        List<VFileResp> list = new ArrayList<>();
        list.add(vFileResp);
        list.add(vFileResp);

        ApiResult<List<VFileResp>> apiResult = new ApiResult<>();
        apiResult.setData(list);
        apiResult.add(new Link("oschina.ne").withSelfRel(),new Link("baidu.com").withRel("baidu"));
        return apiResult;
    }

    @Override
    public ApiResult<String> getFileName(@PathVariable long id) {
        ApiResult<String> r = new ApiResult<>();
        r.setData("a" + id);
        return r;
    }

    @Override
    public String getName() {
        return "abc";
    }
}

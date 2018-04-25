package com.ymu.servicefileclient.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.servicefileclient.api.FastDFSClientApi;
import com.ymu.servicefileclient.service.IFastDFSClientService;
import com.ymu.servicefileclient.vo.resp.VFileResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class FastDFSClientController extends BaseController implements FastDFSClientApi {

    @Autowired
    private IFastDFSClientService fastDFSClientService;

    @Override
    public ApiResult<List<VFileResp>> getAllFile() {
        VFileResp vFileResp = new VFileResp();
        vFileResp.setName("a-1");
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
}

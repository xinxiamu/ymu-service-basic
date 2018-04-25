package com.ymu.servicefileclient.api;

import com.ymu.framework.spring.mvc.api.ApiResult;
import com.ymu.servicefileclient.vo.resp.VFileResp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能简述:<br>
 *  FastDFS文件系统客户端使用服务接口。
 *
 * @author zmt
 * @create 2018-02-10 下午4:41
 * @updateTime
 * @since 1.0.0
 */
@RequestMapping
public interface FastDFSClientApi {

    @GetMapping("${path.fastDFSClient.getAllFile}")
    ApiResult<List<VFileResp>> getAllFile();

}

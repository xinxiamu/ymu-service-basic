package com.ymu.servicefileclient.api;

import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/fastDFSClient")
public interface FastDFSClientApi {

    @GetMapping("/getAllFile")
    List<VFileStorageResp> getAllFile() throws Exception;

    @GetMapping("/getFileName/{id}")
    String getFileName(@PathVariable long id) throws Exception;

    @GetMapping("/getName")
    String getName() throws Exception;

    @GetMapping("/copyDirFromJar")
    String copyDirFromJar() throws Exception;
}

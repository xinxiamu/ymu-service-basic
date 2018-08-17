package com.ymu.servicefileclient.api;

import com.ymu.servicefileclient.vo.req.VFileStorageReq;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 文件存储信息操作。
 */
@RequestMapping("/fileStorage")
public interface FileStorageApi {

    @PostMapping("/save")
    long save(@RequestBody @Validated VFileStorageReq vFileStorageReq);

    @GetMapping("/all")
    List<VFileStorageResp> getAll();
}

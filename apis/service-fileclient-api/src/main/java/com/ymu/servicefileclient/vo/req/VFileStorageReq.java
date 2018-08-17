package com.ymu.servicefileclient.vo.req;

import com.ymu.framework.base.VBase;
import lombok.Data;

@Data
public class VFileStorageReq extends VBase {

    /**
     * 原始名称
     */
    private String orgName;

    /**
     * 存储名称
     */
    private String storeName;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 文件存储所在服务器域名
     */
    private String host;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 标志
     */
    private String tag;
}

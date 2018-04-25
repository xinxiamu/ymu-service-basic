package com.ymu.servicefileclient.vo.resp;

import com.ymu.framework.base.VBase;
import lombok.Data;

/**
 * 功能简述:<br>
 *     文件存储信息。
 *
 * @author zmt
 * @create 2018-04-24 下午3:33
 * @updateTime
 * @since 1.0.0
 */
@Data
public class VFileResp extends VBase {

    /**
     * 文件名称。
     */
    private String name;

    /**
     * 文件路径
     */
    private String url;
}

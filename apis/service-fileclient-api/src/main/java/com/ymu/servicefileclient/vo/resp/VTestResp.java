package com.ymu.service.fileclient.vo.resp;

import com.abs.infrastructure.base.VBase;

/**
 * 功能简述:<br>
 *
 * @author zmt
 * @create 2018-03-06 下午2:41
 * @updateTime
 * @since 1.0.0
 */
public class VTestResp extends VBase {

    private String name;

    private boolean sex;

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

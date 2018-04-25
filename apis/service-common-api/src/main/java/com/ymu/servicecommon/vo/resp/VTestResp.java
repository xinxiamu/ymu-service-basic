package com.ymu.servicecommon.vo.resp;

import com.ymu.framework.base.VBase;

import java.util.Date;

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

    private Boolean flg;

    private Date date;

    public Boolean getFlg() {
        return flg;
    }

    public void setFlg(Boolean flg) {
        this.flg = flg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

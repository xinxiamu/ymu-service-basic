package com.ymu.servicefileclient.common;

import com.ymu.framework.spring.mvc.sensitive.SensitiveCallback;

/**
 * 功能简述:<br>
 *
 * @author zmt
 * @create 2018-04-17 下午10:46
 * @updateTime
 * @since 1.0.0
 */
public class SensitiveDeal implements SensitiveCallback {


    @Override
    public String filter(String s) {
        if ("色情".equals(s)) {
            return "参数中包含敏感词";
        }
        return s;
    }
}

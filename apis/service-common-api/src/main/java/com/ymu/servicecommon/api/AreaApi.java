package com.ymu.servicecommon.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 五级区域地址操作。
 */
@RequestMapping("/area")
public interface AreaApi {

    @GetMapping("/save/{level}")
    long save(@PathVariable int level);
}

package com.ymu.servicecommon.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.servicecommon.api.AreaApi;
import com.ymu.servicecommon.dao.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AreaController extends BaseController implements AreaApi {

    @Autowired
    private AreaDao areaDao;


    @Override
    public long save(@PathVariable int level) {
        areaDao.save(level);
        return 0;
    }


}

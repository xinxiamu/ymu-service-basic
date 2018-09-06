package com.ymu.servicecommon.dao;

import com.ymu.framework.dao.base.BaseDao;
import com.ymu.servicecommon.dao.repository.AreaRepository;

public interface AreaDao extends BaseDao<AreaRepository> {

    void save(int level);
}

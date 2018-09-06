package com.ymu.servicecommon.dao.impl;

import com.ymu.framework.dao.base.BaseDaoImpl;
import com.ymu.servicecommon.dao.AreaDao;
import com.ymu.servicecommon.dao.repository.AreaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AreaDaoImpl extends BaseDaoImpl<AreaRepository> implements AreaDao {

    @Override
    public void save(int level) {


    }
}

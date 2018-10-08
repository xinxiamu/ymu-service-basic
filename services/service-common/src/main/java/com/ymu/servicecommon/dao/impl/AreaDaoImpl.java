package com.ymu.servicecommon.dao.impl;

import com.ymu.framework.dao.base.BaseDaoImpl;
import com.ymu.framework.dao.idgenerator.SnowflakeIdUtils;
import com.ymu.servicecommon.dao.AreaDao;
import com.ymu.servicecommon.dao.repository.AreaRepository;
import com.ymu.servicecommondomain.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AreaDaoImpl extends BaseDaoImpl<AreaRepository, Area> implements AreaDao {

    @Override
    public void save(int level) {
       Area area = new Area();
       area.setId(SnowflakeIdUtils.genId(0,0));

       insert(area);
       insertObj(area);
    }
}

package com.ymu.servicecommon.dao.repository;

import com.ymu.framework.dao.base.BaseRepository;
import com.ymu.servicecommondomain.Area;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AreaRepository extends BaseRepository<Area, Long>, JpaSpecificationExecutor<Area> {

}

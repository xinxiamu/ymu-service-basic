package com.ymu.servicefileclient.dao.impl;

import com.alibaba.fastjson.JSON;
import com.ymu.framework.dao.base.BaseDaoImpl;
import com.ymu.framework.dao.ds.DSInject;
import com.ymu.servicefileclient.config.DSType;
import com.ymu.servicefileclient.dao.FileStorageDao;
import com.ymu.servicefileclient.dao.repository.FileStorageRepository;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import com.ymu.servicefileclientdomain.jooq.generated.tables.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class FileStorageDaoImpl extends BaseDaoImpl<FileStorageRepository> implements FileStorageDao {

    @DSInject(value = DSType.YMU_FILE_SLAVE1)
    @Override
    public List<VFileStorageResp> getAll() {
        List<com.ymu.servicefileclientdomain.FileStorage>  result = jooqDsl.select().from(FileStorage.FILE_STORAGE).limit(0, 10).fetchInto(com.ymu.servicefileclientdomain.FileStorage.class);
        List<com.ymu.servicefileclientdomain.FileStorage> list = mRepository.findAll();
        log.debug(">>>>" + JSON.toJSONString(result));
        return null;
    }
}

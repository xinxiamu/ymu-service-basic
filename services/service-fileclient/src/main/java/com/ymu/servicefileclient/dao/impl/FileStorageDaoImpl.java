package com.ymu.servicefileclient.dao.impl;

import com.ymu.framework.dao.base.BaseDaoImpl;
import com.ymu.framework.dao.ds.DSInject;
import com.ymu.servicefileclient.config.DSType;
import com.ymu.servicefileclient.dao.FileStorageDao;
import com.ymu.servicefileclient.dao.repository.FileStorageRepository;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import com.ymu.servicefileclientdomain.jooq.generated.tables.FileStorage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileStorageDaoImpl extends BaseDaoImpl<FileStorageRepository> implements FileStorageDao {

    @DSInject(value = DSType.YMU_FILE_SLAVE1)
    @Override
    public List<VFileStorageResp> getAll() {
        List<VFileStorageResp> result = jooqDsl.select().from(FileStorage.FILE_STORAGE).limit(0, 10).fetchInto(VFileStorageResp.class);
        return result;
    }
}

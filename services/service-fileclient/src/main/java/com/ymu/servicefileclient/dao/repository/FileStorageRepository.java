package com.ymu.servicefileclient.dao.repository;

import com.ymu.framework.dao.base.BaseRepository;
import com.ymu.servicefileclientdomain.FileStorage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileStorageRepository extends BaseRepository<FileStorage, Long>, JpaSpecificationExecutor<FileStorage> {

}

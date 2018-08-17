package com.ymu.servicefileclient.dao;

import com.ymu.framework.dao.base.BaseDao;
import com.ymu.servicefileclient.dao.repository.FileStorageRepository;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import com.ymu.servicefileclientdomain.FileStorage;

import java.util.List;

public interface FileStorageDao extends BaseDao<FileStorageRepository> {

    List<VFileStorageResp> getAll();
}

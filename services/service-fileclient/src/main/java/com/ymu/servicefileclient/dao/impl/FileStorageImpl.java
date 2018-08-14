package com.ymu.servicefileclient.dao.impl;

import com.ymu.framework.dao.base.BaseDaoImpl;
import com.ymu.servicefileclient.dao.FileStorageDao;
import com.ymu.servicefileclient.dao.repository.FileStorageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FileStorageImpl extends BaseDaoImpl<FileStorageRepository> implements FileStorageDao {

}

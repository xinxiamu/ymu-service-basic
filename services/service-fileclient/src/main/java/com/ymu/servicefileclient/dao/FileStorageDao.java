package com.ymu.servicefileclient.dao;

import com.ymu.servicefileclient.vo.resp.VFileStorageResp;

import java.util.List;

public interface FileStorageDao {

    List<VFileStorageResp> getAll();

}

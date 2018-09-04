package com.ymu.servicefileclient.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.framework.dao.idgenerator.SnowflakeIdWorker;
import com.ymu.framework.utils.BeanUtil;
import com.ymu.servicefileclient.api.FileStorageApi;
import com.ymu.servicefileclient.dao.FileStorageDao;
import com.ymu.servicefileclient.vo.req.VFileStorageReq;
import com.ymu.servicefileclient.vo.req.VFileStorageValidator;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import com.ymu.servicefileclientdomain.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class FileStorageController extends BaseController implements FileStorageApi {

    @Autowired
    private FileStorageDao fileStorageDao;

    @Override
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new VFileStorageValidator());
    }

    @Override
    public long save(VFileStorageReq vFileStorageReq) {
        vFileStorageReq = new VFileStorageReq();
        vFileStorageReq.setHost("http://api.ymu.com");
        vFileStorageReq.setOrgName("身份证");
        vFileStorageReq.setPath("/file/idcard/a.jpg");
        vFileStorageReq.setStoreName("abcdd");
        vFileStorageReq.setSuffix(".jpg");
        vFileStorageReq.setTag("人生得意须尽欢");

        FileStorage fileStorage = new FileStorage();
        BeanUtil.from(vFileStorageReq).to(fileStorage);

        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);
        long id = snowflakeIdWorker.nextId();
        fileStorage.setId(id);

        fileStorageDao.getMRepository().save(fileStorage);

        return fileStorage.getId();
    }

    @Override
    public List<VFileStorageResp> getAll() {
        return fileStorageDao.getAll();
    }
}

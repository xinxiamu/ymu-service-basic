package com.ymu.servicefileclient.dao.impl;

import com.alibaba.fastjson.JSON;
import com.ymu.framework.dao.base.BaseDaoImpl;
import com.ymu.framework.dao.ds.DSInject;
import com.ymu.framework.utils.sql.splice.SqlBuilder;
import com.ymu.servicefileclient.config.DSType;
import com.ymu.servicefileclient.dao.FileStorageDao;
import com.ymu.servicefileclient.dao.repository.FileStorageRepository;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import com.ymu.servicefileclientdomain.FileStorage;
import com.ymu.servicefileclientdomain.FileStorageSqlField;
import jooq.generated.fileclientdomain.tables.Earea_5level;
import jooq.generated.fileclientdomain.tables.daos.FileStorageJqDao;
import jooq.generated.fileclientdomain.tables.pojos.Earea_5levelJqVo;
import jooq.generated.fileclientdomain.tables.pojos.FileStorageJqVo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class FileStorageDaoImpl extends BaseDaoImpl<FileStorageRepository,FileStorage> implements FileStorageDao {

    jooq.generated.fileclientdomain.tables.FileStorage qfs = jooq.generated.fileclientdomain.tables.FileStorage.FILE_STORAGE.as("qfs");
    jooq.generated.fileclientdomain.tables.Earea_5level earea = Earea_5level.EAREA_5LEVEL.as("earea");

    @Autowired
    private FileStorageJqDao fileStorageJqDao;

    @Autowired
    private JdbcTemplate ymuFileJdbcTemplate;

    @DSInject(value = DSType.YMU_FILE_SLAVE1)
    @Override
    public List<VFileStorageResp> getAll() {
//        DataSourceContextHolder.setDS(DSType.YMU_FILE_SLAVE1);
        List<FileStorage> result = jooqDsl.select(qfs.ID).from(qfs).limit(0, 10).fetchInto(FileStorage.class);
        Result<Record1<Long>> a = jooqDsl.select(qfs.ID).from(qfs).fetch();
        List<FileStorage> list = mRepository.findAll();
        log.debug(">>>>" + JSON.toJSONString(result));
        List<FileStorageJqVo> r = fileStorageJqDao.fetchById(123676436321009664L);
        log.debug(">>>r:" + r.size());
        String sql = new SqlBuilder() {
            {
                SELECT(FileStorageSqlField.ID).FROM(FileStorageSqlField.TABLE_NAME);
                WHERE(FileStorageSqlField.ID.concat("=?")).AND();

            }
        }.toString();
        List<Map<String, Object>> jlist = ymuFileJdbcTemplate.queryForList(sql);
        return null;
    }


}

package com.ymu.servicecommon.config.ds;

import com.ymu.framework.dao.ds.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源。
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "ymuDicMasterDataSource")
    @Qualifier("ymuDicMasterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.master")
    public DataSource ymuDicMasterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "ymuDicSlave1DataSource")
    @Qualifier("ymuDicSlave1DataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.slave-1")
    public DataSource ymuDicSlave1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Primary
    @Bean(name = "ymuDicDataSource")
    @Scope("singleton")
    @DependsOn({"ymuDicMasterDataSource","ymuDicSlave1DataSource"}) //要加入这个注解，在数据源初始化之后，再初始化本bean，否则会出现循环依赖注入无法启动。
    public DataSource dynamicDataSource(@Qualifier("ymuDicMasterDataSource") DataSource ymuFileMasterDataSource,
                                        @Qualifier("ymuDicSlave1DataSource") DataSource ymuFileSlave1DataSource) {
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put(DSType.YMU_DIC_MASTER, ymuFileMasterDataSource);
        dsMap.put(DSType.YMU_DIC_SLAVE1, ymuFileSlave1DataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(ymuFileMasterDataSource); // 设置默认数据源
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}

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

    @Bean(name = "ymuCommonMasterDataSource")
    @Qualifier("ymuCommonMasterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.master")
    public DataSource ymuCommonMasterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "ymuCommonSlave1DataSource")
    @Qualifier("ymuCommonSlave1DataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.slave1")
    public DataSource ymuCommonSlave1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "ymuCommonSlave1DataSource")
    @Qualifier("ymuCommonSlave2DataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.slave2")
    public DataSource ymuCommonSlave2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Primary
    @Bean(name = "ymuCommonDataSource")
    @Scope("singleton")
    @DependsOn({"ymuCommonMasterDataSource","ymuCommonSlave1DataSource","ymuCommonSlave2DataSource"}) //要加入这个注解，在数据源初始化之后，再初始化本bean，否则会出现循环依赖注入无法启动。
    public DataSource dynamicDataSource(@Qualifier("ymuCommonMasterDataSource") DataSource ymuCommonMasterDataSource,
                                        @Qualifier("ymuCommonSlave1DataSource") DataSource ymuCommonSlave1DataSource,@Qualifier("ymuCommonSlave2DataSource") DataSource ymuCommonSlave2DataSource) {
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put(DSType.YMU_COMMON_MASTER, ymuCommonMasterDataSource);
        dsMap.put(DSType.YMU_COMMON_SLAVE1, ymuCommonSlave1DataSource);
        dsMap.put(DSType.YMU_COMMON_SLAVE2, ymuCommonSlave2DataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(ymuCommonMasterDataSource); // 设置默认数据源,主库
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}

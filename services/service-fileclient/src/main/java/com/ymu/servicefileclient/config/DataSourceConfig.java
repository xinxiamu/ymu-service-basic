package com.ymu.servicefileclient.config;

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

//    @Bean(name = "${datasource.ymu-file.name.master}")
//    @Qualifier("${datasource.ymu-file.name.master}")
//    @ConfigurationProperties(prefix="spring.datasource.hikari.master")
//    @Primary
    @Bean(name = "ymuFileMasterDataSource")
    @Qualifier("ymuFileMasterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.master")
    public DataSource ymuFileMasterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

//    @Bean(name = "${datasource.ymu-file.name.slave-1}")
//    @Qualifier("${datasource.ymu-file.name.slave-1}")
//    @ConfigurationProperties(prefix="spring.datasource.hikari.slave-1")
    @Bean(name = "ymuFileSlave1DataSource")
    @Qualifier("ymuFileSlave1DataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.slave-1")
    public DataSource ymuFileSlave1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Primary
    @Bean(name = "dataSource")
    @Scope("singleton")
    @DependsOn({"ymuFileMasterDataSource","ymuFileSlave1DataSource"}) //要加入这个注解，在数据源初始化之后，再初始化本bean，否则会出现循环依赖注入无法启动。
    public DataSource dynamicDataSource(@Qualifier("ymuFileMasterDataSource") DataSource ymuFileMasterDataSource,
                                        @Qualifier("ymuFileSlave1DataSource") DataSource ymuFileSlave1DataSource) {
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put(DSType.YMU_FILE_MASTER.name(), ymuFileMasterDataSource);
        dsMap.put(DSType.YMU_FILE_SLAVE1.name(), ymuFileSlave1DataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(ymuFileMasterDataSource); // 设置默认数据源
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}

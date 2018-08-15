package com.ymu.servicefileclient.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

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
    @Primary
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

}

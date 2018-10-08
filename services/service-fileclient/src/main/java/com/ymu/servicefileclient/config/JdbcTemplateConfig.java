package com.ymu.servicefileclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class JdbcTemplateConfig {

    @Autowired
    @Qualifier(value = "ymuFileDataSource")
    private DataSource ymuFileDataSource;

    /**
     * spring jdbc。
     *
     * @return
     */
    @Bean(name = "jdbcTemplate")
    @Qualifier("jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(ymuFileDataSource);
    }
}

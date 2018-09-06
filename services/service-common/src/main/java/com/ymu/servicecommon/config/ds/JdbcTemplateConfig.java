package com.ymu.servicecommon.config.ds;

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
    @Qualifier(value = "ymuDicDataSource")
    private DataSource ymuDicDataSource;

    /**
     * spring jdbc。
     *
     * @return
     */
    @Bean(name = "ymuDicJdbcTemplate")
    @Qualifier("ymuDicJdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(ymuDicDataSource);
    }
}

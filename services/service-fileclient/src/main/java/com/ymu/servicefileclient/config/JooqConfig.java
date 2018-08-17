package com.ymu.servicefileclient.config;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
//@AutoConfigureAfter(DataSourceConfig.class)
public class JooqConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider() {
        DataSourceConnectionProvider dp = new DataSourceConnectionProvider(dataSource);
        return dp;
    }

    private ExceptionTranslator exceptionTranslator() {
        return new ExceptionTranslator();
    }

    @Bean
    public DefaultConfiguration jooqConfig(DataSourceConnectionProvider dataSourceConnectionProvider) {
        DefaultConfiguration config = new DefaultConfiguration();
        config.setSQLDialect(SQLDialect.MYSQL);
        config.setConnectionProvider(dataSourceConnectionProvider);
//        config.setTransactionProvider(transactionProvider());

        DefaultExecuteListenerProvider defaultExecuteListenerProvider = new DefaultExecuteListenerProvider(exceptionTranslator());
        config.setExecuteListenerProvider(defaultExecuteListenerProvider);

        return config;
    }

    @Bean
    public DefaultDSLContext jooqDsl(DefaultConfiguration jooqConfig) {
        DefaultDSLContext dsl = new DefaultDSLContext(jooqConfig);
        return dsl;
    }
}

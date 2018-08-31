package com.ymu.servicefileclient.config;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({"jooq.generated"})
@AutoConfigureAfter(DataSourceConfig.class)
public class MyJooqConfig {

    @Autowired
    @Qualifier("ymuFileDataSource")
    private DataSource ymuFileDataSource; // 数据源

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(ymuFileDataSource));
    }

    @Bean
    public DefaultConfiguration configuration() {
        DefaultConfiguration config = new DefaultConfiguration();
        config.setSQLDialect(SQLDialect.MYSQL);
        config.setConnectionProvider(connectionProvider());
//        config.setTransactionProvider(ymuFileDataSource);//事务

        DefaultExecuteListenerProvider defaultExecuteListenerProvider = new DefaultExecuteListenerProvider(new ExceptionTranslator());
        config.setExecuteListenerProvider(defaultExecuteListenerProvider);

        return config;
    }

    @Bean
    public DefaultDSLContext jooqDsl() {
        DefaultDSLContext dsl = new DefaultDSLContext(configuration());
        return dsl;
    }
}

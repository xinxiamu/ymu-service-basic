package com.ymu.servicecommon.config.ds;

import com.ymu.framework.spring.config.AbstractJooqConfig;
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
public class MyJooqConfig extends AbstractJooqConfig {

    @Autowired
    @Qualifier("ymuDicDataSource")
    private DataSource ymuDicDataSource; // 数据源


    @Override
    public DataSource dataSource() {
        return ymuDicDataSource;
    }
}

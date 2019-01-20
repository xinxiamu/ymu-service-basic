package com.ymu.servicecommon.config.ds;

import com.ymu.framework.dao.base.BaseRepositoryFactoryBean;
import com.ymu.servicecommon.common.Constants;
import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryYmuCommon", transactionManagerRef = "transactionManagerYmuCommon", basePackages = {
		Constants.YMU_COMMON_REPOSITORY_PACKAGE_PATH }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class YmuCommonDBConfig {

	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier("ymuCommonDataSource")
	private DataSource ymuCommonDataSource; // 数据源

	@Bean(name = "entityManagerFactoryYmuCommon")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryYmuCommon(EntityManagerFactoryBuilder builder) {
		checkAndResetDatasource();

		LocalContainerEntityManagerFactoryBean emFactory = builder.dataSource(ymuCommonDataSource).properties(getVendorProperties())
				.packages(Constants.YMU_COMMON_DOMAIM_PACKAGE_PATH) // 设置数据表对应实体类所在位置
				.persistenceUnit("ymuCommon").build(); //设置持久化管理工厂别名

		return emFactory;
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, Object> getVendorProperties() {
		HibernateSettings hibernateSettings = new HibernateSettings();
		return jpaProperties.getHibernateProperties(hibernateSettings);
	}

	@Primary
	@Bean(name = "transactionManagerYmuCommon")
	public PlatformTransactionManager transactionManagerYmuCommon(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryYmuCommon(builder).getObject());
	}

	private void checkAndResetDatasource() {
		if (environment.acceptsProfiles("dev") || environment.acceptsProfiles("test")
				|| environment.acceptsProfiles("update")) {// log4jdbc打印sql日志
			this.ymuCommonDataSource = new DataSourceSpy(ymuCommonDataSource);
		}
	}

}
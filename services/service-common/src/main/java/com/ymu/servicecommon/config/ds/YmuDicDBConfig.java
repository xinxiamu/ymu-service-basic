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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryYmuDic", transactionManagerRef = "transactionManagerYmuDic", basePackages = {
		Constants.YMU_DIC_REPOSITORY_PACKAGE_PATH }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class YmuDicDBConfig {

	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier("ymuDicDataSource")
	private DataSource ymuDicDataSource; // 数据源

	@Bean(name = "entityManagerFactoryYmuDic")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryYmuDic(EntityManagerFactoryBuilder builder) {
		checkAndResetDatasource();

		LocalContainerEntityManagerFactoryBean b = builder.dataSource(ymuDicDataSource).properties(getVendorProperties())
				.packages(Constants.YMU_DIC_DOMAIM_PACKAGE_PATH) // 设置数据表对应实体类所在位置
				.persistenceUnit("ymDic").build(); //设置持久化管理工厂别名

		return b;
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, Object> getVendorProperties() {
		HibernateSettings hibernateSettings = new HibernateSettings();
		return jpaProperties.getHibernateProperties(hibernateSettings);
	}

	@Primary
	@Bean(name = "transactionManagerYmuDic")
	public PlatformTransactionManager transactionManagerYmuDic(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryYmuDic(builder).getObject());
	}

	private void checkAndResetDatasource() {
		if (environment.acceptsProfiles("dev") || environment.acceptsProfiles("test")
				|| environment.acceptsProfiles("update")) {// log4jdbc打印sql日志
			this.ymuDicDataSource = new DataSourceSpy(ymuDicDataSource);
		}
	}

}
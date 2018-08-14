package com.ymu.servicefileclient.config;

import com.ymu.framework.dao.base.BaseRepositoryFactoryBean;
import com.ymu.servicefileclient.common.Constants;
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

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryYmuFileSlave1", transactionManagerRef = "transactionManagerYmuFileSlave1", basePackages = {
		Constants.YMU_FILE_REPOSITORY_PACKAGE_PATH }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class YmuFileSlave1Config {

	@Autowired
	Environment environment;

	@Autowired
	@Qualifier("${datasource.ymu-file.name.slave-1}")
	private DataSource ymuFileSlave1DataSource; // 数据源

	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryYmuFileSlave1(builder).getObject().createEntityManager();
	}

	@Bean(name = "entityManagerFactoryYmuFileSlave1")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryYmuFileSlave1(EntityManagerFactoryBuilder builder) {
		if (environment.acceptsProfiles("dev") || environment.acceptsProfiles("test")
				|| environment.acceptsProfiles("update")) {// log4jdbc打印sql日志
			ymuFileSlave1DataSource = new DataSourceSpy(ymuFileSlave1DataSource);
		}
		return builder.dataSource(ymuFileSlave1DataSource).properties(getVendorProperties())
				.packages(Constants.YMU_FILE_DOMAIM_PACKAGE_PATH) // 设置数据表对应实体类所在位置
				.persistenceUnit("ymuFileSlave1").build(); //设置持久化管理工厂别名
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, Object> getVendorProperties() {
		HibernateSettings hibernateSettings = new HibernateSettings();
		return jpaProperties.getHibernateProperties(hibernateSettings);
	}

	@Primary
	@Bean(name = "transactionManagerYmuFileSlave1")
	public PlatformTransactionManager transactionManagerYmuFileSlave1(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryYmuFileSlave1(builder).getObject());
	}

}
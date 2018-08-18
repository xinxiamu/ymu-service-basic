package com.ymu.servicefileclient.config;

import com.ymu.framework.dao.base.BaseRepositoryFactoryBean;
import com.ymu.framework.spring.jpa.JpaCommentWriter;
import com.ymu.servicefileclient.common.Constants;
import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryYmuFile", transactionManagerRef = "transactionManagerYmuFile", basePackages = {
		Constants.YMU_FILE_REPOSITORY_PACKAGE_PATH }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class YmuFileDBConfig {

	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier("ymuFileDataSource")
	private DataSource ymuFileDataSource; // 数据源

/*	@Bean
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryYmuFile(builder).getObject().createEntityManager();
	}*/

	/*@Bean("ymuFileJpaCommentWriter")
	@Profile("dev")
	public JpaCommentWriter jpaCommentWriter(EntityManagerFactoryBuilder builder) {
		JpaCommentWriter jpaCommentWriter = new JpaCommentWriter();
		jpaCommentWriter.setEntityManagerFactory(entityManagerFactoryYmuFile(builder).getObject());
		return jpaCommentWriter;
	}*/

	@Bean(name = "entityManagerFactoryYmuFile")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryYmuFile(EntityManagerFactoryBuilder builder) {
		checkAndResetDatasource();

		LocalContainerEntityManagerFactoryBean b = builder.dataSource(ymuFileDataSource).properties(getVendorProperties())
				.packages(Constants.YMU_FILE_DOMAIM_PACKAGE_PATH) // 设置数据表对应实体类所在位置
				.persistenceUnit("ymuFile").build(); //设置持久化管理工厂别名

		return b;
	}

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, Object> getVendorProperties() {
		HibernateSettings hibernateSettings = new HibernateSettings();
		return jpaProperties.getHibernateProperties(hibernateSettings);
	}

	@ConfigurationProperties(prefix = "spring.ips.jpa")
	@Bean(name = "ipsJpaProperties")
	@Primary
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	@Primary
	@Bean(name = "transactionManagerYmuFile")
	public PlatformTransactionManager transactionManagerYmuFile(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryYmuFile(builder).getObject());
	}

	private void checkAndResetDatasource() {
		if (environment.acceptsProfiles("dev") || environment.acceptsProfiles("test")
				|| environment.acceptsProfiles("update")) {// log4jdbc打印sql日志
			this.ymuFileDataSource = new DataSourceSpy(ymuFileDataSource);
		}
	}

}
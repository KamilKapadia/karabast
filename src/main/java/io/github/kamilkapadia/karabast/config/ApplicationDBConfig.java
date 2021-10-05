package io.github.kamilkapadia.karabast.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;


//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//		entityManagerFactoryRef = "applicationEntityManagerFactory", basePackages = {
//		"io.github.kamilkapadia.karabast.components" })
public class ApplicationDBConfig {

//	@Primary
//	@Bean(name = "applicationDataSourceProperties")
//	@ConfigurationProperties("application.datasource")
//	public DataSourceProperties dataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Primary
//	@Bean(name = "applicationDataSource")
//	@ConfigurationProperties("application.datasource.configuration")
//	public DataSource dataSource(@Qualifier("applicationDataSourceProperties") DataSourceProperties dataSourceProperties) {
//		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//				.build();
//	}
//
//	@Primary
//	@Bean(name = "applicationEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//			EntityManagerFactoryBuilder builder, @Qualifier("applicationDataSource") DataSource applicationDataSource) {
//		return builder
//				.dataSource(applicationDataSource)
//				.packages("io.github.kamilkapadia.karabast.components")
//				.persistenceUnit("application")
//				.build();
//	}
//
//	@Primary
//	@Bean(name = "applicationTransactionManager")
//	public PlatformTransactionManager transactionManager(
//			@Qualifier("applicationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//		return new JpaTransactionManager(entityManagerFactory);
//	}
}


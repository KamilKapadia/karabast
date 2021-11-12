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
//		entityManagerFactoryRef = "logEntityManagerFactory", basePackages = {
//		"io.github.kamilkapadia.karabast.log" })
public class LogDBConfig {
//	
//	@Bean(name = "logDataSourceProperties")
//	@ConfigurationProperties("log.datasource")
//	public DataSourceProperties dataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "logDataSource")
//	@ConfigurationProperties("log.datasource.configuration")
//	public DataSource dataSource(@Qualifier("logDataSourceProperties") DataSourceProperties logDataSourceProperties) {
//		return logDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//				.build();
//	}
//	
//	@Bean(name = "logEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//			EntityManagerFactoryBuilder builder, @Qualifier("logDataSource") DataSource logDataSource) {
//		return builder
//				.dataSource(logDataSource)
//				.packages("io.github.kamilkapadia.karabast.log")
//				.persistenceUnit("log")
//				.build();
//	}
//	
//	@Bean(name = "logTransactionManager")
//	public PlatformTransactionManager transactionManager(
//			@Qualifier("logEntityManagerFactory") EntityManagerFactory logEntityManagerFactory) {
//		return new JpaTransactionManager(logEntityManagerFactory);
//	}
}

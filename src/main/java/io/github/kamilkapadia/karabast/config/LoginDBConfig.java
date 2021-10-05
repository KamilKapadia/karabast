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
//		entityManagerFactoryRef = "loginEntityManagerFactory", basePackages = {
//		"io.github.kamilkapadia.karabast.login" })
public class LoginDBConfig {
	
//	@Bean(name = "loginDataSourceProperties")
//	@ConfigurationProperties("login.datasource")
//	public DataSourceProperties dataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "loginDataSource")
//	@ConfigurationProperties("login.datasource.configuration")
//	public DataSource dataSource(@Qualifier("loginDataSourceProperties") DataSourceProperties loginDataSourceProperties) {
//		return loginDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//				.build();
//	}
//	
//	@Bean(name = "loginEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//			EntityManagerFactoryBuilder builder, @Qualifier("loginDataSource") DataSource loginDataSource) {
//		return builder
//				.dataSource(loginDataSource)
//				.packages("io.github.kamilkapadia.karabast.login")
//				.persistenceUnit("login")
//				.build();
//	}
//	
//	@Bean(name = "loginTransactionManager")
//	public PlatformTransactionManager transactionManager(
//			@Qualifier("loginEntityManagerFactory") EntityManagerFactory loginEntityManagerFactory) {
//		return new JpaTransactionManager(loginEntityManagerFactory);
//	}
}

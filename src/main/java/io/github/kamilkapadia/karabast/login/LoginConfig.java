package io.github.kamilkapadia.karabast.login;

//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
//@EnableJpaRepositories(
//		basePackages = "com.example.demo.login", 
//		entityManagerFactoryRef = "loginEntityManager", 
//		transactionManagerRef = "loginTransactionManager"
//		)
public class LoginConfig {
//	@Autowired
//	private Environment env;
//
//	@Bean
//	@Primary
//	public DataSource loginDataSource() {
//
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl(env.getProperty("spring.datasource.url"));
//		dataSource.setUsername(env.getProperty("spring.datasource.user"));
//		dataSource.setPassword(env.getProperty("spring.datasource.pass"));
//
//		return dataSource;
//	}
//
//	@Bean
//	@Primary
//	public LocalContainerEntityManagerFactoryBean loginEntityManager() {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(loginDataSource());
//		em.setPackagesToScan(new String[] { "com.example.demo.login" });
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		HashMap<String, Object> properties = new HashMap<>();
//		properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
//		properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
//		em.setJpaPropertyMap(properties);
//
//		return em;
//	}
//
//	@Bean
//	@Primary
//	public PlatformTransactionManager loginTransactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(loginEntityManager().getObject());
//
//		return transactionManager;
//	}
}
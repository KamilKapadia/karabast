package io.github.kamilkapadia.karabast;

import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//@Configuration
public class SpringConfiguration {
	
//	@Bean
//	@Primary
//	@ConfigurationProperties(prefix="application.datasource")
//	public DataSource primaryDataSource() {
//	    return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix="login.datasource")
//	public DataSource loginDataSource() {
//	    return DataSourceBuilder.create().build();
//	}
}

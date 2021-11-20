package io.github.kamilkapadia.karabast.login;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource securityDataSource;
	
	@Value("${login.datasource.url}")
	private String dbUrl;
	@Value("${login.datasource.username}")
	private String username;
	@Value("${login.datasource.password}")
	private String password;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Use JDBC authentication
		auth.jdbcAuthentication().dataSource(getSecurityDataSource());
	}
	
	private DataSource getSecurityDataSource() {
	    DataSourceBuilder<?> builder = DataSourceBuilder.create()
	        .url(dbUrl)
	        .username(username)
	        .password(password);

	    return builder.build();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").anonymous()
		.antMatchers("/").permitAll()
		.antMatchers("/home/**").hasRole("USER")
		.antMatchers("/jobs/**").hasRole("USER")
		.antMatchers("/job-details/**").hasRole("USER")
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/authenticateTheUser")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");

		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/**").permitAll()
		.antMatchers(HttpMethod.PATCH, "/api/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
		.and()
		.csrf().disable();
	}
}
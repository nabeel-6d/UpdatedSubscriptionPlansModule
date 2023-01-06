package com.example.subscriptions.databaseconfig.plan;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.subscriptions.LoggerImpl.MyLogger;

@Configuration
@PropertySources({ @PropertySource("classpath:application.properties")})
@EnableJpaRepositories(
		entityManagerFactoryRef = "secondaryEntityManagerFactory",
		transactionManagerRef = "secondaryTransactionManager",
		basePackages = {"com.example.subscriptions.repository.plan"}
		)
public class DataSourceConfigForPlan {
		
	@Value("${spring.secondary.datasource.url}")
    private String url;
	
	@Value("${spring.secondary.datasource.username}")
    private String username;
	
	@Value("${spring.secondary.datasource.password}")
    private String password;
	
	@Value("${spring.secondary.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${spring.jpa.show-sql}")
	private String showSql;
	
	@Value("${spring.jpa.properties.hibernate.format_sql}")
	private String formatSql;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateddlauto;


	@Bean(name = "secondaryDbDataSource")
	public DataSource secondaryDbDataSource(){
        return DataSourceBuilder.create()
        		.url(url)
        		.username(username)
        		.password(password)
				.driverClassName(driverClassName)
        		.build();
    }
	
	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory() {
		MyLogger.trace("in plan secondaryEntityManagerFactory");

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(secondaryDbDataSource());

		//setting packages to scan for entities
		em.setPackagesToScan("com.example.subscriptions.entities.subscriber.plan");
		em.setPersistenceUnitName("ForDatabase-Two");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		//setting jpa & hibernate properties
		HashMap<String,Object> properties = new HashMap<>();
		properties.put("hibernate.dialect",hibernateDialect);
		properties.put("hibernate.show_sql",showSql);
		properties.put("hibernate.format_sql", formatSql);
		properties.put("hibernate.hbm2ddl.auto",hibernateddlauto);

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		return em;
	}
	
	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager(){
		MyLogger.trace("in plan secondaryTransactionManager");
		JpaTransactionManager tm = new JpaTransactionManager(secondaryEntityManagerFactory().getObject());
		return tm;
	}

}

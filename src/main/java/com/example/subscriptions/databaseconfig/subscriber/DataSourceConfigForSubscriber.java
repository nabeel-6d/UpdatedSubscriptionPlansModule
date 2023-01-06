package com.example.subscriptions.databaseconfig.subscriber;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
		entityManagerFactoryRef = "primaryEntityManagerFactory",
		transactionManagerRef = "primaryTransactionManager",
		basePackages = {"com.example.subscriptions.repository.subscriber"}
		)
public class DataSourceConfigForSubscriber {
	
	@Value("${spring.primary.datasource.url}")
    private String url;
	
	@Value("${spring.primary.datasource.username}")
    private String username;
	
	@Value("${spring.primary.datasource.password}")
    private String password;
	
	@Value("${spring.primary.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${spring.jpa.show-sql}")
	private String showSql;

	@Value("${spring.jpa.properties.hibernate.format_sql}")
	private String formatSql;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateddlauto;

	@Primary
	@Bean(name = "primaryDbDataSource")
    public DataSource primaryDbDataSource(){
        return DataSourceBuilder.create()
        		.url(url)
        		.username(username)
        		.password(password)
				.driverClassName(driverClassName)
        		.build();
    }
	
	@Primary
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
		MyLogger.trace("in subscriber primaryEntityManagerFactory");
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(primaryDbDataSource());

		//setting packages to scan for entities
		em.setPackagesToScan("com.example.subscriptions.entities.subscriber");
		em.setPersistenceUnitName("ForDatabase-One");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		// vendorAdapter.setDatabase(Database.MYSQL);
		em.setJpaVendorAdapter(vendorAdapter);

		//setting jpa & hibernate properties
		HashMap<String,Object> properties = new HashMap<>();
		properties.put("hibernate.dialect",hibernateDialect);
		properties.put("hibernate.show_sql",showSql);
		properties.put("hibernate.format_sql",formatSql);
		properties.put("hibernate.hbm2ddl.auto",hibernateddlauto);

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		return em;
	}
	
	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager() {
		MyLogger.trace("in subscriber primaryTransactionManager");
		JpaTransactionManager tm =  new JpaTransactionManager(primaryEntityManagerFactory().getObject());
		return tm;
	}
}

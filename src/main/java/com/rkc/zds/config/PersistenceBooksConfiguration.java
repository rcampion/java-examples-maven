package com.rkc.zds.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL55Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.rkc.zds" }, excludeFilters = {
		@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION) })
@EnableJpaRepositories(basePackages = "com.rkc.zds.jpa.repository", entityManagerFactoryRef = "booksEntityManager", transactionManagerRef = "booksTransactionManager")
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class PersistenceBooksConfiguration {

	@Bean
	public DataSource booksDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		//dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("books_user");
		dataSource.setPassword("ChangeIt");
		// dataSource.setUrl("jdbc:mysql://localhost:3306/auth?createDatabaseIfNotExist=true");
		dataSource.setUrl(
				"jdbc:mysql://localhost:3306/books?createDatabaseIfNotExist=true&serverTimezone=UTC&useLegacyDatetimeCode=false");

		return dataSource;
	}

	@Bean(name = "booksEntityManager")
	public LocalContainerEntityManagerFactoryBean booksEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(booksDataSource());
		em.setPackagesToScan(new String[] { "com.rkc.zds.jpa.entity" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public JpaVendorAdapter booksJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager booksTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(booksEntityManager().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor booksExceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/*
	 * @Bean PasswordEncoder getEncoder() { return new BCryptPasswordEncoder(); }
	 */
	Properties additionalProperties() {
		Properties properties = new Properties();
		
		///properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");		
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.dialect", MySQL55Dialect.class.getName());
		//properties.put("hibernate.show_sql", Boolean.TRUE.toString());
		properties.put("hibernate.show_sql", Boolean.FALSE.toString());		
		properties.put("hibernate.query.jpaql_strict_compliance", Boolean.FALSE.toString());
		properties.put("driverClassName","com.mysql.cj.jdbc.Driver");
		properties.put("url","jdbc:mysql://localhost:3306/books?createDatabaseIfNotExist=true&serverTimezone=UTC&useLegacyDatetimeCode=false");
		properties.put("userName" ,"book_user");
		properties.put("password" ,"ChangeIt");
		
		return properties;
	}
}

package com.rkc.zds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.rkc.zds")
@SpringBootApplication
public class SpringBootExampleApp extends SpringBootServletInitializer {
	
	private static Log logger = LogFactory.getLog(SpringBootExampleApp.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootExampleApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExampleApp.class, args);
    }

/*
@ComponentScan("com.rkc.zds")
@SpringBootApplication
public class SpringBootExampleApp {

	private static Log logger = LogFactory.getLog(SpringBootExampleApp.class);

	public static void main(String[]args) {
		SpringApplication.run(SpringBootExampleApp.class, args);
	}
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				logger.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("ServletContext destroyed");
			}
		};
	}
*/
}

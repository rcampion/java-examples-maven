package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rkc.zds.config.AppConfig;
import com.rkc.zds.dto.Company;

class SpringBeanTest {

	@Test
	void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Company company = context.getBean("company", Company.class);

		assertEquals("High Street", company.getAddress().getStreet());
		assertEquals(1000, company.getAddress().getNumber());
	}
}

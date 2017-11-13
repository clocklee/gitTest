package com.mis.infosys.persist.repos.IgnoredTest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mis.infosys.Application;
@Ignore
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@EnableAutoConfiguration
@DataJpaTest
/*@EntityScan(basePackages={"com.mis.infosys.persist.entities.manufacture",
						  "com.mis.infosys.persist.entities.rbac",
						  "com.mis.infosys.persist.entities.records"})*/
public class Test_JPAData {
	
	
	
	@Test
	public void test()
	{
		
	}

}

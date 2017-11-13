package com.mis.infosys.persist.repos.something_reserved_JPARepos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mis.infosys.Application;
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
public class Test_SpringMVC {
	
	@Test
	public void test001()
	{
		System.out.println("Test_SpringMVC.test001()");
	}
	
	

}

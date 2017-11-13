package com.mis.infosys.persist.repos;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mis.infosys.Application;
import com.mis.infosys.persist.repositories.impl.rbac.RoleRepos;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleRepoTest implements IReposTest{ /*extends AbsReposTest{
	public RoleRepoTest(String testMethod)
	{
		super(testMethod);
	}*/
	
	private static final Logger logger = LoggerFactory.getLogger(RoleRepoTest.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private static RoleRepos roleRepos;
	@Test
	@Override
	public void Test001_StartPrint() {
//		System.out.println("RoleRepoTest.Test001_StartPrint()");
		logger.info("RoleRepoTest.Test001_StartPrint");
		
	}
	@Override
	public void Test002_insertAndFind() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Test003_updateAndFind() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Test004_delete() {
		// TODO Auto-generated method stub
		
	}


	

}

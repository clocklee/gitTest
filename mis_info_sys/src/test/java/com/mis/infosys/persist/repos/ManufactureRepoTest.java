package com.mis.infosys.persist.repos;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mis.infosys.Application;
import com.mis.infosys.persist.repositories.impl.manufactures.ManufactureRepos;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManufactureRepoTest implements IReposTest { /*extends AbsReposTest{
	
	public ManufactureRepoTest(String testMethod)
	{
		super(testMethod);
	}*/
	
	@SuppressWarnings("unused")
	@Autowired
	private static ManufactureRepos manufactureRepos;
	@Test
	public void Test001_StartPrint() {
		System.out.println("ManufactureRepoTest.Test001_StartPrint()");		
	}
	@Test
	@Override
	public void Test002_insertAndFind() {
		
		
	}
	@Test
	@Override
	public void Test003_updateAndFind() {
		
		
	}
	@Test
	@Override
	public void Test004_delete() {
		
		
	}


	
	
}

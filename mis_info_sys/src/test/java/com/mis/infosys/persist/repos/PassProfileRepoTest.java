package com.mis.infosys.persist.repos;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mis.infosys.Application;
import com.mis.infosys.persist.repositories.impl.rbac.PassProfileRepos;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PassProfileRepoTest {/*extends AbsReposTest{
	
	public PassProfileRepoTest(String testMethod)
	{
		super(testMethod);
	}*/
	
	
	@Autowired
	private static PassProfileRepos passproRepos;
	@Test
	//@Override
	public void Test001_StartPrint() {

		System.out.println("PassProfileRepoTest.Test001_StartPrint()");
		
	}

	//@Override
	public void Test002_insert() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void Test003_find() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void Test003_update() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void Test004_delete() {
		// TODO Auto-generated method stub
		
	}
	
	

}

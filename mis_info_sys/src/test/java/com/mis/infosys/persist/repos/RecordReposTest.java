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
import com.mis.infosys.persist.repositories.impl.records.RecordRepos;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecordReposTest implements IReposTest {/*extends AbsReposTest{
	public RecordReposTest(String testMethod)
	{
		super(testMethod);
	}*/
	@Autowired
	private static RecordRepos manuLogRepos;
	
	@Test
	@Override
	public void Test001_StartPrint() {
		System.out.println("RecordReposTest.Test001_StartPrint()");
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

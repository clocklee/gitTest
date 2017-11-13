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
import com.mis.infosys.persist.repositories.impl.manufactures.ProduceRepos;
import com.mis.infosys.persist.entities.manufactures.*;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProduceRepoTest implements IReposTest{ 	
	
	@Autowired
	public static ProduceRepos produceRepos;
	@Test
	@Override
	public void Test001_StartPrint() {
		System.out.println("ProduceRepoTest.Test001_StartPrint()");
	}

	@Test
	@Override
	public void Test002_insertAndFind() {
		
		
		
		Produce produce = new Produce();
		
		
		
		
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

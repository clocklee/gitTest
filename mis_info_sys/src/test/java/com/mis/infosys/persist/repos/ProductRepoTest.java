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
import com.mis.infosys.persist.repositories.impl.manufactures.ProductRepos;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRepoTest implements IReposTest{/* extends AbsReposTest{
	public ProductRepoTest(String testMethod)
	{
		super(testMethod);
	}*/
	@Autowired
	private ProductRepos productRepos;
	@Test
//	@Override
	public void Test001_StartPrint() {
		System.out.println("ProductRepoTest.Test001_StartPrint()");
		
	}

	@Override
	public void Test002_insertAndFind() {
		
		
	}

	@Override
	public void Test003_updateAndFind() {
		
	}

	@Override
	public void Test004_delete() {
		
	}
	

}

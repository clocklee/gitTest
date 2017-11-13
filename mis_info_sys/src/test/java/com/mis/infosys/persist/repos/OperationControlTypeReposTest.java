package com.mis.infosys.persist.repos;



import java.util.List;

import org.apache.logging.slf4j.Log4jLogger;
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
import org.springframework.util.Assert;

import com.mis.infosys.Application;
import com.mis.infosys.persist.entities.manufactures.OperationControlType;
import com.mis.infosys.persist.repositories.impl.rbac.OperationControlTypeRepos;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperationControlTypeReposTest implements IReposTest{ /*extends AbsReposTest{
	
	public OperationControlTypeReposTest(String testMethod)
	{
		super(testMethod);
	}*/
	//private static final Log4jLogger log = Logger.getLogger(OperationControlTypeReposTest.class);
	private static final Logger logger = LoggerFactory.getLogger(OperationControlTypeReposTest.class);
	@Autowired
	private  OperationControlTypeRepos OCTRepos;
	@Test
	//@Override
	public void Test001_StartPrint() {
		System.out.println("OperationControlTypeReposTest.Test001_StartPrint()");
		
	}
	@Override
	public void Test002_insertAndFind() {
		// TODO Auto-generated method stub
		String[] names =new String[]{"root","admin","member","customer","technical","authority"};		
		for(String name : names)
		{
			OperationControlType oct = new OperationControlType();
			oct.setName(name);
			this.OCTRepos.save(oct);
		}		
	    Assert.isTrue(this.OCTRepos.getTotalCount() == names.length);
	}
	@Override
	public void Test003_updateAndFind() {
		// TODO Auto-generated method stub
		 List<OperationControlType> octes = this.OCTRepos.getAll();
		 
		 for(OperationControlType oct : octes)
		 {
			 oct.setDescript(oct.getName());
			 this.OCTRepos.update(oct);
		 }
		 
		 octes = this.OCTRepos.getAll();
		 for(OperationControlType oct : octes)
		 {
			 Assert.isTrue(oct.getName().equals(oct.getDescript()));
		 }		
	}
	@Override
	public void Test004_delete() {
		 
		OperationControlType oct = this.OCTRepos.getAll().get(5);
		
		this.OCTRepos.delete(oct.getId());
		
		Assert.isTrue(this.OCTRepos.isExist("id", oct.getId()));
		
	}

	
	
	
}

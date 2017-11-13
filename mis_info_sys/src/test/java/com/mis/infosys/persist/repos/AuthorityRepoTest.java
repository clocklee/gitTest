package com.mis.infosys.persist.repos;

import java.util.List;

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
import org.springframework.util.Assert;

import com.mis.infosys.Application;
import com.mis.infosys.persist.entities.rbac.Authority;
import com.mis.infosys.persist.repositories.impl.rbac.AuthorityRepos;

import junit.framework.TestCase;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorityRepoTest implements IReposTest  {	
	
	@Autowired
	private  AuthorityRepos authRepos;

	
	@Test
	public void Test001_StartPrint() {		
		System.out.println("AuthorityRepoTest.Test001_StartPrint()");		
	}

	@Test
	public void Test002_insertAndFind() {
		String[] names = new String[]{"admin","member","trannee","customer","deleteTest"};

		Authority authority = new Authority();
		authority.setName("root");
		authority.setLevel(0);
		Long savedId = (Long)this.authRepos.save(authority);
		
		Authority entity = this.authRepos.get(savedId);
		
		Assert.notNull(entity);
		
		
		int level = 0;
		for(String name : names)
		{			
			Authority auth = new Authority();
			auth.setName(name);
			auth.setLevel(level++);			
			this.authRepos.save(auth);
		}
		
		List<Authority> authes = this.authRepos.getAll();
		
		Assert.notEmpty(authes);
		Assert.isTrue(authes.size()>1);
		
	}	

	@Test
	public void Test003_updateAndFind() {

		Authority entity = this.authRepos.getAll().get(0);
		
		Assert.notNull(entity);
		
		String descript = entity.getName();
		entity.setDescript(descript);
		
		this.authRepos.update(entity);
		
		entity = this.authRepos.get(entity.getId());
		
		Assert.isTrue(entity.getDescript().equals(entity.getName()));
		
		boolean isExist = this.authRepos.isExist("descript", entity.getDescript());
		
		Assert.isTrue(isExist);
		
	}

	@Test
	public void Test004_delete() {		
		
		Long size  = this.authRepos.getTotalCount();	
		
		
		Authority entity = this.authRepos.getAll().get(size.intValue()-1);	
		
		Assert.notNull(entity);
		
	    this.authRepos.delete(entity.getId());	    
	    boolean isExist = this.authRepos.isExist("name", "deleteTest");
	    Assert.isTrue(!isExist);		
		
		
	}
	
	
	
	

}

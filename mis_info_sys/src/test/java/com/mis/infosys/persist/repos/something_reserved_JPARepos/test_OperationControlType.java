package com.mis.infosys.persist.repos.something_reserved_JPARepos;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mis.infosys.Application;
import com.mis.infosys.persist.entities.manufactures.OperationControlType;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.OperationControlTypeRepositoryImpl;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test_OperationControlType {
	@Autowired
	private OperationControlTypeRepositoryImpl reposImpl;	
	private static Long id;
	
	@Test
	public void Test001_Save()
	{
		OperationControlType entity = new OperationControlType();
		entity.setName("admin");
	    id =(Long)reposImpl.save(entity);	    
	    Assert.isTrue(id>0);
	}
	
	@Test	
	public void Test002_Get()
	{
		OperationControlType entity = reposImpl.get(id);
		Assert.isTrue(entity.getId()>0&entity.getName().equals("admin"));
		Assert.notNull(entity);
	}
}

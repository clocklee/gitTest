package com.mis.infosys.persist.repos.something_reserved_JPARepos;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.tools.Trace;
import org.apache.logging.log4j.LogManager;


import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mis.infosys.Application;
import com.mis.infosys.persist.entities.rbac.Authority;
import com.mis.infosys.persist.repositories.impl.rbac.remove.AuthorityRepositoryImpl;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test_AuthorityRepository {
	
	@Autowired
	private AuthorityRepositoryImpl authorityReposImpl;
	
	//private final static Logger logger =  LogManager.getLogger(test_persist.class.getName());	
	private static Authority authority_save;
	private static Long saved_id;
	private static List<Authority> authorities;
	private static String msg;	
	
//	@Ignore
	@Test
	public void test001_AuthorityRepository_GetAll()
	{		
		List<Authority> authorities = authorityReposImpl.getAll();		
		msg = "AuthorityRepository.getAll Authorities function";		
		Assert.notEmpty(authorities,"ErrorCode:test001_001\t"+msg);		
		//logger.info(msg);		
	}
	
//	@Ignore
	@Test 
	public void test02_AuthorityService_save()
	{
		authority_save = new Authority();
		authority_save.setName("customer");
		authority_save.setLevel(2);
		authority_save.setDescript("");		
		saved_id = (Long)authorityReposImpl.save(authority_save);		
		msg="AuthorityRepository.save Authority function";		
		Assert.isTrue(saved_id>0,"ErrorCode:test001_002\t"+msg);		
	}
//	@Ignore
	@Test
	public void test03_AuthorityService_get()
	{	
		Authority authority = authorityReposImpl.get(saved_id);		
		msg="AuthorityRepository.get Authorit by Authority's id function";
		Assert.isTrue(authority.getId().equals(saved_id)&authority.getName().equals("customer"),"ErrorCode:test001_003\t"+msg);
		
		//logger.info(msg);
	}
//	@Ignore
	@Test
	public void test04_AuthorityService_update()
	{
		authority_save = authorityReposImpl.get(saved_id);		
		authority_save.setDescript("this is a test!");
		 authorityReposImpl.update(authority_save);
		msg="AuthorityRepository.upate by Authority";			
		System.out.println("test_persist.test04_AuthorityService_delete()");
		String getFromSession = authorityReposImpl.get(authority_save.getId()).getDescript();
		Assert.isTrue(getFromSession.equals(authority_save.getDescript()),"ErrorCode:test001_005\t"+msg);		
	}
	
	
	
	@Ignore
	@Test
	public void test06_AuthorityService_delete()
	{
		//saved_id=(long) 20;
		authorityReposImpl.delete(saved_id);		
		//authorityReposImpl.delete(authority_save);
		msg="AuthorityRepository.delete by Authorit";		
//		if(authorities.contains(authority_save))
//		{
//			System.out.println("test_persist.test05_AuthorityService_update()");
//		}		
		//authorityReposImpl.isExist(propertyName, value)
		boolean isExist = authorityReposImpl.isExist("id", saved_id);
		Assert.isTrue(!isExist,"ErrorCode:test001_006\t"+msg);
		//logger.info(msg);
	}
	
	

}

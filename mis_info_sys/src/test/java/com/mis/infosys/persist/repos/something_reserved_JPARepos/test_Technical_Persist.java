package com.mis.infosys.persist.repos.something_reserved_JPARepos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mis.infosys.Application;
import com.mis.infosys.persist.entities.manufactures.Technical;
import com.mis.infosys.persist.repositories.Dao.IHibernateBaseDao;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.TechnicalRepositoryImpl;
import com.mis.infosys.utils.PageFinder;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)

@SpringApplicationConfiguration(Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test_Technical_Persist  {
	
	@Autowired
	private TechnicalRepositoryImpl reposImpl;	
	
	private static Long id;
	@Ignore
	@Test
	public void Test001_Get()
	{
		Long getId = 30L;
		Technical entity = reposImpl.get(getId);
		Assert.notNull(entity);		
	}	
	
	@Test
	public void Test002_save()
	{
		Technical entity = new Technical();
		entity.setName("mpi test");
		entity.setDescript("this is a test");
		entity.setPerRequesite(false);
		entity.setUseControl(true);
		entity.setPerRequesite(false);
		id = (Long)reposImpl.save(entity);		
		Technical saved = reposImpl.get(id);		
		Assert.isTrue(saved.getName().equals(entity.getName()));
		
	}
	
	@Test
	public void Test003_saveOrUpdate()
	{
		Technical entity = reposImpl.get(id);
		String descript = String.valueOf(id)+":"+ entity.getDescript();
		entity.setDescript(descript);
		entity = reposImpl.saveOrUpdate(entity);
		Assert.isTrue(descript.equals(entity.getDescript()));		
	}
	
	@Test
	public void Test004_getCount()
	{
		Long size = this.reposImpl.getTotalCount();		
		Assert.isTrue(size>0);
	}
	
	@Test
	public void Test005_Update()
	{		
		Technical entity = reposImpl.get(id);
		String descript = String.valueOf(id)+":"+ entity.getDescript()+":update test";
		entity.setDescript(descript);
		reposImpl.update(entity);
		Assert.isTrue(descript.equals(entity.getDescript()));		
	}
	
	@Test
	public void Test006_getList()
	{
		List<Technical> list= reposImpl.getAll();
		System.out.println("######################\ntest_Technical_Persist.Test006_getList()\n:List size = "+list.size());
		Assert.isTrue(list.size()>0);
	}
	@Ignore
	@Test
	public void Test007_delete()
	{
		//Technical entity = reposImpl.get(id);
		reposImpl.delete(id);
		boolean isExist = reposImpl.isExist("id", id);
		Assert.isTrue(!isExist);
	}
}

package com.mis.infosys.persist.repos;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
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
import com.mis.infosys.persist.entities.manufactures.MisProcess;
import com.mis.infosys.persist.repositories.impl.manufactures.MisProcessRepos;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessRepoTest  implements IReposTest {		
	
	@Autowired
	private MisProcessRepos misprocessRepos;
	@Test
	@Override
	public void Test001_StartPrint() {
		System.out.println("ProcessRepoTest.Test001_StartPrint()");		
	}
	@Override
	public void Test002_insertAndFind() {
		
		String[] names = new String[]{"MPI","Transient","AmplifyTest","LoseTest","Test"};
		
		for(String name : names)
		{		
			MisProcess misPro = new MisProcess();
			misPro.setName(name);
			misPro.setIslock(false);	   
			
			String createTime = DateTime.now().toString();//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//new DateTimeFormatter("yyyy-MM-dd HH:mm:ss"))
			 misPro.setCreateTime(createTime);
			 
			this.misprocessRepos.save(misPro);
		}		
		Assert.isTrue(this.misprocessRepos.getTotalCount()>0);
	}
	@Override
	public void Test003_updateAndFind() {
		
		List<MisProcess> misProes = this.misprocessRepos.getAll();
		
		for(MisProcess misPro : misProes)
		{
			misPro.setDescription(misPro.getName());
			this.misprocessRepos.update(misPro);
		}
		
		misProes = this.misprocessRepos.getAll();
		for(MisProcess misPro:misProes)
		{
			Assert.isTrue(misPro.getName().equals(misPro.getDescription()));
		}
		
	}
	@Override
	public void Test004_delete() {
		// TODO Auto-generated method stub
		
		Long count = this.misprocessRepos.getTotalCount();
		MisProcess entity = this.misprocessRepos.getAll().get(count.intValue()-1);
		
		this.misprocessRepos.delete(entity.getId());
		
		Assert.isTrue(!this.misprocessRepos.isExist("id", entity.getId()));
		
	}


	
	

}

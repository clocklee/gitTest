package com.mis.infosys.persist.repos.something_reserved_JPARepos;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mis.infosys.Application;
import com.mis.infosys.persist.entities.manufactures.OperationControlType;
import com.mis.infosys.persist.entities.manufactures.Technical;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.OperationControlTypeRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.TechnicalRepositoryImpl;

/***
 * this test is properbly to be a demo for 
 * how to build the class about entity's OneToMany or ManyToOne relationship.
 * @attention this demo is very importent for hibernate's annotations 
 * @author admin
 *
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_TechnicalAndOperationControlType {

	@Autowired
	private TechnicalRepositoryImpl techRepos;
	@Autowired
	private OperationControlTypeRepositoryImpl OCTRepos;	
	
	/**
	 * 向数据库中添加数据并测试,
	 * 一般测试情况下忽略不测
	 */
	//@Ignore
	@Test
	public void Test001_Save_TechnicalAndOperationControlType()
	{		
		List<OperationControlType> octes = new ArrayList<OperationControlType>();
		String[] octNames = new String[]{"root","admin","member","customer"};
		String desc = "the Operation can controlled by usr with authority of ";
		
		for(String name : octNames)
		{
			OperationControlType oct = new OperationControlType();
			oct.setName(name);
			oct.setDescript(desc+name);
			this.OCTRepos.save(oct);
		}		
		octes = this.OCTRepos.getAll();		
		Assert.notEmpty(octes);
		String[] operationName = new String[]{"MPI","Transient","Sweep Optical"};
		for(String name : operationName)
		{
			Technical tec = new Technical();
			tec.setName(name);
			//tec.setOperationControlType(octes.get(1));
			tec.setPerRequesite(false);
			tec.setUseControl(true);
			tec.setDescript("MPI Test Tecnical");			
			Long tecId=(Long)this.techRepos.save(tec);
			System.out.println("Test_ReposImpls.Test_OneToMany():"+tecId);
		}
		
		List<Technical> teches = this.techRepos.getAll();
		Assert.notNull(teches);
		
		for(Technical  tech : teches)
		{			
			System.out.println(tech.getId()+":"+tech.toString());
		}		
		
	}
	//@Ignore
	@Test
	public void Test002_Save_Technical_WithExistOperationControlType()
	{		
		Technical tech = new Technical();
		OperationControlType oct = this.OCTRepos.getAll().get(0);
		tech.setName("Hardwave Test");
		tech.setDescript("this is junit test");
		tech.setOperationControlType(oct);
		tech.setPerRequesite(true);
		tech.setUseControl(true);
		
		Technical entity =null;
		Long tech_id;
		
		tech_id = (Long)this.techRepos.save(tech);		
		entity = this.techRepos.get(tech_id);
		
		Assert.notNull(entity);		
		
		OperationControlType oct_tech = entity.getOperationControlType();
		
		Long oct_id = oct_tech.getId();		
		
		Assert.isTrue(oct.getId()==oct_id);		
		
	}
	
	//@Ignore
	@Test 
	/***
	 *update technical's attribute of operationControlType to test manytoOne relationship about hibernate's cascade setting;
	 */
	public void Test003_update_Technical_WithExistOperationControlType()
	{
		List<Technical> technicals = techRepos.getAll();
		List<OperationControlType> octes = this.OCTRepos.getAll();
		int index = -1;
		for(Technical tech : technicals)
		{			
			if(octes.size() < index++)				
			{
				break;
			}
			OperationControlType oct = octes.get(index);
			 tech.setOperationControlType(oct);			
			 this.techRepos.update(tech);//.saveOrUpdate(tech);	
			 
			 
		Technical tech_test = this.techRepos.get(tech.getId());
		Assert.isTrue(tech_test.getOperationControlType().getId().equals(oct.getId()));
			 
		}
		
		
	}
	@Test
	public void Test004_Save_Technical_WithNotExistOperationControlType()
	{
		OperationControlType octNew = new OperationControlType();
		octNew.setName("trainee");
		octNew.setDescript("this is controlled by trainee");
		
		Long octId=(Long) this.OCTRepos.save(octNew);
		
		octNew = this.OCTRepos.get(octId);
		
		
		Technical tech = new Technical();
		tech.setName("SoftWare Test");
		tech.setOperationControlType(octNew);
		tech.setPerRequesite(false);
		tech.setUseControl(true);				
		Long tech_id = (Long)this.techRepos.save(tech);		
		tech = this.techRepos.get(tech_id);
		Assert.notNull(tech);
		Assert.notNull(tech.getOperationControlType().getName().equals(octNew.getName()));
		
	}
	@Ignore
	@Test
	public void Test005_Delete_Technical_WithExistTechnical()
	{
		
		Technical tech = this.techRepos.getAll().get(0);
		
		this.techRepos.delete(tech.getId());
		
		Long tech_id = tech.getId();
		
		Long oct_id = tech.getOperationControlType().getId();
		
		boolean techIsExist = this.techRepos.isExist("id", tech_id);
		
		boolean octIsExist = this.OCTRepos.isExist("id", oct_id);
		
		Assert.isTrue(!techIsExist);
		
		Assert.isTrue(octIsExist);
		
	}
	
	@Ignore
	@Test
	public void Test006_Delete_OperationControlType_WithUnused()
	{
		this.OCTRepos.delete(55L);
		OperationControlType oct = this.OCTRepos.get(55L);
		
		Assert.isNull(oct);	
		
		
	}
	@Ignore
	@Test
	public void Test007_Delete_OperationControlType_WithUsed()
	{
		
		OperationControlType oct = this.OCTRepos.getAll().get(1);
		
		List<Technical> teches = oct.getTechnicals();		
		
		Long[] tech_ids = new Long[teches.size()];
		int index = -1;
		for(Technical tech : teches)
		{
			tech_ids[++index] = tech.getId();
		}
		
		this.OCTRepos.delete(oct.getId());
		
		for(Long id : tech_ids)
		{
			Technical tech = this.techRepos.get(id);
			Assert.isNull(tech.getOperationControlType());
		}
	}
	
	
	
	
	
	@Test
	public void Test008_ShowRelationShip()
	{
		
		List<OperationControlType> octes = OCTRepos.getAll();	
		List<Technical> technicals = techRepos.getAll();
		
		System.out.println(octes.get(1).toString());

		for(Technical  tech : technicals)
		{			
			System.out.println("Test_ReposImpls.Test001_manyToOne()");
			System.out.println(tech.getId());//+":"+tech.toString());
		}

		for(OperationControlType oct : octes)
		{
			System.out.println("Test_ReposImpls.Test099_ShowRelationShip()");
			System.out.println(oct.getId());//+":"+oct.toString());
			//System.out.println(oct.getTechnicals().size());			
		}
		
	}
	
	
	
}

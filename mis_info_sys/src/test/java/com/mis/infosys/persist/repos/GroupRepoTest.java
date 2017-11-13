package com.mis.infosys.persist.repos;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
import com.mis.infosys.persist.entities.rbac.UGroup;
import com.mis.infosys.persist.repositories.impl.rbac.GroupRepos;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupRepoTest implements IReposTest {
	
	@SuppressWarnings("unused")
	@Autowired
	private GroupRepos groupRepos;

	@Test
	@Override
	public void Test001_StartPrint() {
		System.out.println("GroupRepoTest.Test001_StartPrint()");
		
	}
	
	@Test
	@Override
	public void Test002_insertAndFind() {	
		
		
		String[][] groups = new String[][]{
			{"oa.corp","Optical Amplify Corporation"},
			{"r&d dp1","Research & Develop Department 1"},
			{"mdp1","Manufacture Department"},
			{"swg","Software group"},
			{"hwg","Hardware group"},
			{"eswg","Embed Software group"},
			{"mg","Machnical Group"},
			{"og","Optical Group"},
			{"tg","Test Group"},
			{"领料线","生产一部领料线"},
			{"光钎制作线","生产一部光钎制作线"},
			{"电路焊接线","生产一部电路焊接线"},
			{"电路测线","生产一部电路测试线"},
			{"光路测线","生产一部光路测试线"},
			{"生产工程师组","生产一部工程师组"}
		};
		
		UGroup oac = new UGroup();
		
		oac.setName(groups[0][0]);
		oac.setDescript(groups[0][1]);
		
		UGroup rd1 = new UGroup();
		rd1.setName(groups[1][0]);
		rd1.setDescript(groups[1][1]);
		
		rd1.setParent(oac);
		
		UGroup md1 = new UGroup();
		md1.setName(groups[2][0]);
		md1.setDescript(groups[2][1]);
		rd1.setParent(oac);
		List<UGroup> childGroups = new ArrayList<UGroup>(){
			{
				add(rd1);
				add(md1);
			}
		};		
		
		Long oacId = (Long)this.groupRepos.save(oac);
		
		UGroup entity = this.groupRepos.get(oacId);
		
		Assert.notNull(entity);		
		
		for(String[] groupArray : groups)
		{
		
			UGroup group = new UGroup();
			group.setName(groupArray[0]);
			group.setDescript(groupArray[1]);
			
			this.groupRepos.save(group);
		}
		
		
	}

	@Test
	@Override
	public void Test003_updateAndFind() {
		
		UGroup oabd = this.groupRepos.getAll().get(0);
		oabd.setDescript("Optical Amplify Business Department");
		oabd.setName("oabd");
		
		this.groupRepos.merge(oabd);
		
		oabd = this.groupRepos.get(oabd.getId());
		
		Assert.isTrue(oabd.getName().equals("oabd"));
		
		UGroup rd1 = this.groupRepos.getAll().get(1);
		UGroup md1 = this.groupRepos.getAll().get(2);
		UGroup swg = this.groupRepos.getAll().get(3);
		
		rd1.setParent(oabd);
		md1.setParent(oabd);
		
		this.groupRepos.merge(rd1);
		this.groupRepos.merge(md1);
		
	    oabd = this.groupRepos.get(oabd.getId());
	    
	    List<UGroup> childs = oabd.getChilds();
	    
	    Assert.notNull(childs);
	    
	    rd1 = this.groupRepos.get(rd1.getId());
	    md1 = this.groupRepos.get(md1.getId());
	    
	    Assert.notNull(rd1.getParent());
	    Assert.notNull(md1.getParent());
		
	}
	
	@Test
	@Override
	public void Test004_delete() {
		
		
		
	}

	

	
}

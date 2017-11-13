package com.mis.infosys.persist.repos.something_reserved_JPARepos;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mis.infosys.Application;

import com.mis.infosys.persist.entities.rbac.*;
import com.mis.infosys.persist.repositories.impl.manufactures.*;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.ManufactureRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.MisProcessRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.OperationControlTypeRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.ProduceRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.ProductRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.manufactures.remove.TechnicalRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.rbac.*;
import com.mis.infosys.persist.repositories.impl.rbac.remove.AuthorityRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.rbac.remove.PassProfileRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.rbac.remove.UGroupRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.rbac.remove.URoleRepositoryImpl;
import com.mis.infosys.persist.repositories.impl.rbac.remove.UsrRepositoryImpl;

/***
 * this test is the core for the persist's test;
 * in this test,we will test all of the relationships about user,group,role and technical;
 * the user can be belong more than one group;
 * the group to be another group's father or child;
 * then the group will has some roles; 
 * also,the role has some technical what can be allowed to do, and has authority in the group;
 * sometimes,the user has different role in different groups.
 * so,the technical is look like authority;
 * 
 * @author admin
 *
 */
@Ignore
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)//webEnvironment=WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
@FixMethodOrder(value= MethodSorters.NAME_ASCENDING)
public class Test_Usr_Group_Role_Technical_AuthorityRepositoryImpl {
	@Autowired
	private UsrRepositoryImpl usrRepo;
	@Autowired
	private UGroupRepositoryImpl ugroupRepo;
	@Autowired
	private URoleRepositoryImpl uroleRepo;
	@Autowired
	private AuthorityRepositoryImpl authorityRepo;	
	@Autowired
	private TechnicalRepositoryImpl techRepo;	
	@Autowired
	private OperationControlTypeRepositoryImpl opCtypeRepo;
	
	@Autowired
	private PassProfileRepositoryImpl passProfRepos;
	

	@Autowired
	private ManufactureRepositoryImpl mfRepo;
	@Autowired
	private ProduceRepositoryImpl produceRepo;
	@Autowired
	private ProductRepositoryImpl productRepo;
	@Autowired
	private MisProcessRepositoryImpl misproRepos;	
	
//	@Autowired
//	private UsrService usrJpaService;
		
	
	
	@Test
	public void test001()
	{
		usrRepo.getAll();
		ugroupRepo.getAll();
		uroleRepo.getAll();
		authorityRepo.getAll();
		techRepo.getAll();
		opCtypeRepo.getAll();
		
		
		this.mfRepo.getAll();
		this.produceRepo.getAll();
		this.misproRepos.getAll();
		this.productRepo.getAll();
		this.passProfRepos.getAll();
		
//		this.usrJpaService.findAll();
	}
	
	@Test
	public void Test002_Init_Data()
	{
		
		String[][] groupDescs = new String[][]{
				{"rd1","R&D Department 1 for Optical Amplify"},
				{"swg","Software Group"}
		};
		for(String[] groupDesc : groupDescs )
		{
			UGroup group = new UGroup();
			group.setName("R&D Department 1");
			group.setDescript("research and develop department 1 for optical ammplify");
		}	
		
	}
	
	
	

}

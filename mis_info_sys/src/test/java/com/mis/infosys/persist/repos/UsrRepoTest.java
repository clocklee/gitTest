package com.mis.infosys.persist.repos;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;

import com.mis.infosys.persist.repositories.impl.rbac.UsrRepos;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsrRepoTest implements IReposTest{ /*extends AbsReposTest{
	public UsrRepoTest(String testMethod)
	{
		super(testMethod);
	}*/

	@Autowired
	private static UsrRepos usrRepos;
	@Test
	@Override
	public void Test001_StartPrint() {
		System.out.println("UsrRepoTest.Test001_StartPrint()");
	}	

	@Override
	public void Test002_insertAndFind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Test003_updateAndFind() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Test004_delete() {
		// TODO Auto-generated method stub
		
	}
	
	

}

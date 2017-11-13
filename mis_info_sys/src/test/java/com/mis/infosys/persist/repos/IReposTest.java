package com.mis.infosys.persist.repos;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import junit.framework.TestCase;



public interface IReposTest{ /*extends TestCase{
	
	public AbsReposTest(String testMethod)
	{
		super(testMethod);
	}*/

	
	public  void Test001_StartPrint();	
	
	public  void Test002_insertAndFind();
	
	public 	void Test003_updateAndFind();
	
	public 	void Test004_delete();


	
	
	
	
}

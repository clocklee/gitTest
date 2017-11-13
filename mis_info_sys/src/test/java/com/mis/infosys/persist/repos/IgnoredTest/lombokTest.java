package com.mis.infosys.persist.repos.IgnoredTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.mis.infosys.persist.entities.Dog;

import org.junit.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class lombokTest {
	@Test
	public void Test()
	{	
		Dog dog = new Dog();
		dog.setId(123L);
		dog.setUsername("wow");
		dog.setPassword("wof");
		System.out.println(dog.toString());
        Gson	gson = new Gson();	
        System.out.println(gson.toJson(dog));
		
	}

}

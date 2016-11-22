package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.UniqueAbility;

public class UniqueAbilityManagerTest {
	
	
	UniqueAbilityManager personManager = new UniqueAbilityManager();
	
	private final static String NAME_1 = "Zenek";
	private final static int YOB_1 = 1945;
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		UniqueAbility person = new UniqueAbility(NAME_1, YOB_1);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person));
		
		List<UniqueAbility> persons = personManager.getAllPersons();
		UniqueAbility personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(YOB_1, personRetrieved.getYob());
		
	}

}

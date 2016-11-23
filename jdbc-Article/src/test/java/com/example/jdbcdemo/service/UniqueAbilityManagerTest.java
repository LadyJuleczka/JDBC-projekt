package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.UniqueAbility;

public class UniqueAbilityManagerTest {
	
	
	UniqueAbilityManager uaM = new UniqueAbilityManager();
	
	private final static String NAME_1 = "Tanczace Ostrze";
	private final static String DESC_1 = "Szybkosc ataku zwiekszona o 20%";
	
	
	@Test
	public void checkConnection(){
		assertNotNull(uaM.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		UniqueAbility ua = new UniqueAbility(NAME_1, DESC_1);
		
		uaM.clearUniqueAbilitiy();
		assertEquals(1,uaM.addUniqueAbility(ua)); //sprawdza czy wykonalo sie query
		
	System.out.println("siemka");	
	System.out.println(uaM.addUniqueAbility(ua));
		
		List<UniqueAbility> persons = uaM.getAllUniqueAbility();
		UniqueAbility personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(DESC_1, personRetrieved.getDescription());
		
	}

}

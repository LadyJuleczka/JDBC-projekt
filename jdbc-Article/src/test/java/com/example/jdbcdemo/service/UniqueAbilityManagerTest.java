package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.UniqueAbility;
import com.example.jdbcdemo.domain.Article;

import com.example.jdbcdemo.service.*;

public class UniqueAbilityManagerTest {
	
	
	UniqueAbilityManager uaManager = new UniqueAbilityManager();
	ArticleManager aManager = new ArticleManager();
	
	private final static String NAME_1 = "Tanczace Ostrze";
	private final static String DESC_1 = "Szybkosc ataku zwiekszona o 20%";
	
	
//	@Test
//	public void checkConnection(){
//		assertNotNull(uaManager.getConnection());
//	}
	
//	@Test
//	public void checkAdding(){
//		uaManager.clearUniqueAbilitiy();
//		
//		UniqueAbility ua = new UniqueAbility(NAME_1, DESC_1);
//		
//		uaManager.clearUniqueAbilitiy();
//		aManager.clearArticles();
//		
//
//		assertEquals(1,uaManager.addUniqueAbility(ua)); //sprawdza czy wykonalo sie query
//		
//	System.out.println("siemka");	
//	System.out.println(uaManager.addUniqueAbility(ua));
//		
//		List<UniqueAbility> uniAbilities = uaManager.getAllUniqueAbility();
//		UniqueAbility personRetrieved = uniAbilities.get(0);
//		
//		assertEquals(NAME_1, personRetrieved.getName());
//		assertEquals(DESC_1, personRetrieved.getDescription());
//		
//	}

}

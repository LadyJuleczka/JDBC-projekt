package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Article;

public class ArticleManagerTest {
	
	
	ArticleManager personManager = new ArticleManager();
	
	private final static String NAME_1 = "Zenek";
	private final static int YOB_1 = 1945;
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Article person = new Article(NAME_1, YOB_1);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person));
		
		List<Article> persons = personManager.getAllPersons();
		Article personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(YOB_1, personRetrieved.getYob());
		
	}

}

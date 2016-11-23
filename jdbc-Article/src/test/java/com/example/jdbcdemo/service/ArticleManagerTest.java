package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Article;
import com.example.jdbcdemo.domain.UniqueAbility;
import com.example.jdbcdemo.service.*;

public class ArticleManagerTest {
	
	
	ArticleManager articleManager = new ArticleManager();
	UniqueAbilityManager uaM = new UniqueAbilityManager();
	
	private final static String NAME_1 = "Ostrze nieskonczonosci";
	private final static int DMG_1 = 420;
	private final static String TYPE_1 = "Miecz";
	
	@Test
	public void checkConnection(){
		assertNotNull(articleManager.getConnection());
	}
	
	@Test
	public void sprawdzenie(){
		UniqueAbility ua = new UniqueAbility("Niezlomnyqe", "Obrazenia krytyczne zwiekszone o 20%qwe");
		assertEquals(1,uaM.addUniqueAbility(ua));	
		
		System.out.println("Czy pokaze id?2");
		System.out.println(uaM.select_id_from_uniqueAbility("Niezlomnyqe"));
		
	}	
	
	
	@Test
	public void checkAdding(){
		UniqueAbility ua = new UniqueAbility("Niezlomny", "Obrazenia krytyczne zwiekszone o 20%");
		assertEquals(1,uaM.addUniqueAbility(ua));	
		
		System.out.println("Czy pokaze id?");
		System.out.println(uaM.select_id_from_uniqueAbility("Niezlomny"));
		
		Article article = new Article(NAME_1, DMG_1, TYPE_1, uaM.select_id_from_uniqueAbility("Niezlomny"));
//		
//		articleManager.clearArticles();
		System.out.println("dodalo sie?");
	
		assertEquals(1,articleManager.addArticle(article));
		System.out.println(articleManager.addArticle(article));
			
		List<Article> articles = articleManager.getAllArticle();
		Article articleRetrieved = articles.get(0);
		
		assertEquals(NAME_1, articleRetrieved.getName());
		assertEquals(DMG_1, articleRetrieved.getDmg());
		
	}

}

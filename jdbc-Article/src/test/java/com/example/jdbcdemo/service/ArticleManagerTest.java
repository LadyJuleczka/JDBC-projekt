package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Article;
import com.example.jdbcdemo.domain.UniqueAbility;

public class ArticleManagerTest {
	
	
	ArticleManager articleManager = new ArticleManager();
	UniqueAbilityManager uam = new UniqueAbilityManager();
	
	private final static String NAME_1 = "Ostrze nieskonczonosci";
	private final static int DMG_1 = 420;
	private final static String TYPE_1 = "Miecz";
	
	@Test
	public void checkConnection(){
		assertNotNull(articleManager.getConnection());
	}
	
//	@Test
//	public void checkAdding(){
//		uam.addUniqueAbility(new UniqueAbility("Niezlomny", "Obrazenia krytyczne zwiekszone o 20%"));
//		Article article = new Article(NAME_1, DMG_1, TYPE_1, uam.select_id_from_uniqueAbility("Niezlomny"));
//		
//		articleManager.clearArticles();
//		assertEquals(1,articleManager.addArticle(article));
//		
//		List<Article> articles = articleManager.getAllArticle();
//		Article articleRetrieved = articles.get(0);
//		
//		assertEquals(NAME_1, articleRetrieved.getName());
//		assertEquals(DMG_1, articleRetrieved.getDmg());
//		
//	}

}

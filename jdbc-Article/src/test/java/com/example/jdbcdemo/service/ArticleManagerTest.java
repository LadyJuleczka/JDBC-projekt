package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Article;
import com.example.jdbcdemo.domain.UniqueAbility;


public class ArticleManagerTest {
	
	
	ArticleManager articleManager = new ArticleManager();
	
	private final static String MODEL_1 = "D100";
	private final static int SHOTS_1 = 80000;
	private final static double PRICE_1 = 200;
	private final static boolean SOLD_1 = false;
	
	private final static String NAME_1 = "bag";
	private final static double PRICE_2 = 69;
	
	private final static String NEWMODEL_1 = "D200";
	private final static int NEWSHOTS_1 = 75000;
	private final static double NEWPRICE_1 = 450;
	private final static boolean NEWSOLD_1 = true;
	
	@Before 
	public void initialize() {
		UniqueAbility abi = new UniqueAbility();
		abi.setDesc(MODEL_1);
		abi.setPower(PRICE_1);
		abi.setMagic(SOLD_1);
		abi.setLevel(SHOTS_1);
		
		articleManager.clearUniqueAbility();
		articleManager.clearArticle();
		
		articleManager.addUniqueAbility(abi);
	}
	
	@Test
	public void checkConnection(){
		assertNotNull(articleManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		articleManager.clearUniqueAbility();
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(MODEL_1);
		ua.setPower(PRICE_1);
		ua.setMagic(SOLD_1);
		ua.setLevel(SHOTS_1);
		
		articleManager.clearUniqueAbility();
		articleManager.clearArticle();
		
		assertEquals(1,articleManager.addUniqueAbility(ua));
		List<UniqueAbility> uas = articleManager.getAllUniqueAbility();
		UniqueAbility uasRetrieved = uas.get(0);
		
		assertEquals(MODEL_1, uasRetrieved.getDesc());
		assertEquals(PRICE_1, uasRetrieved.getPower(), 0.1);
		assertEquals(SOLD_1, uasRetrieved.getMagic());
		assertEquals(SHOTS_1, uasRetrieved.getLevel());

	}
	
	@Test
	public void checkAddingSecond() {		
		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		
		Article article = new Article();
		article.setName(NAME_1);
		article.setDmg(PRICE_2);
		article.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, article);
		
		Article artRetrived = abiRetrieved.getArticle().get(0);
//		System.out.println("t");
//		System.out.println(artRetrived.getName());
//		System.out.println(artRetrived.getDmg());
		
		assertEquals(NAME_1, artRetrived.getName());
		assertEquals(PRICE_2, artRetrived.getDmg(), 0.1);
	}
	
	@Test
	public void ArticleGet() {

		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		
		Article article = new Article();
		article.setName(NAME_1);
		article.setDmg(PRICE_2);
		article.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, article);
		
		unis = articleManager.getAllUniqueAbility();
		abiRetrieved = unis.get(0);
		
		List<Article> art = articleManager.getAllArticle(abiRetrieved.getId());
		Article artRetrived = art.get(0);
		assertEquals(1, art.size());
		assertEquals(NAME_1, artRetrived.getName());
		assertEquals(PRICE_2, artRetrived.getDmg(), 0.1);
	}
	
	@Test
	public void EditUniqueAbility() {
		List<UniqueAbility> uniabi = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = uniabi.get(0);
		abiRetrieved.setDesc(NEWMODEL_1);
		abiRetrieved.setPower(NEWPRICE_1);
		abiRetrieved.setLevel(NEWSHOTS_1);
		abiRetrieved.setMagic(NEWSOLD_1);
		articleManager.editUniqueAbility(abiRetrieved.getId(), abiRetrieved);
		
		uniabi = articleManager.getAllUniqueAbility();
		UniqueAbility edited = uniabi.get(0);
		assertEquals(NEWMODEL_1, edited.getDesc());
		assertEquals(NEWPRICE_1, edited.getPower(), 0.01);
		assertEquals(NEWSHOTS_1, edited.getLevel());
		assertEquals(NEWSOLD_1, edited.getMagic());
	}

	@Test
	public void CameraDelete(){

		UniqueAbility abi = new UniqueAbility();
		abi.setDesc(NEWMODEL_1);
		abi.setPower(NEWPRICE_1);
		abi.setMagic(NEWSOLD_1);
		abi.setLevel(NEWSHOTS_1);
		
		articleManager.addUniqueAbility(abi);
		
		List<UniqueAbility> abis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = abis.get(0);
		
		assertEquals(2, abis.size());
		
		Article accessory = new Article();
		accessory.setName(NAME_1);
		accessory.setDmg(PRICE_2);
		accessory.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, accessory);

		abis = articleManager.getAllUniqueAbility();
		abiRetrieved = abis.get(0);

	    // deleting the user
		articleManager.deleteUniqueAbility(abiRetrieved);
		abis = articleManager.getAllUniqueAbility();
	    
	    // checking deletion
	    assertEquals(1, abis.size());
	    
	    UniqueAbility uniabi2 = abis.get(0);
		assertEquals(NEWMODEL_1, uniabi2.getDesc());
		assertEquals(NEWPRICE_1, uniabi2.getPower(), 0.1);
		assertEquals(NEWSOLD_1, uniabi2.getMagic());
		assertEquals(NEWSHOTS_1, uniabi2.getLevel());
	}
}

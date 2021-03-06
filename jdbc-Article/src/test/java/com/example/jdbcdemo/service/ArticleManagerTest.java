package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Article;
import com.example.jdbcdemo.domain.UniqueAbility;


public class ArticleManagerTest {      //SELECT * FROM UNIQUEABILITY
									   //DROP SCHEMA PUBLIC CASCADE
	
	ArticleManager articleManager = new ArticleManager();
	
	private final static String DESC_1 = "Obrazenia krytyczne zwiekszone o 20%";
	private final static int LEVEL_1 = 10;
	private final static double POWER_1 = 420;
	private final static boolean Magic_1 = false;
	
	private final static String NAME_1 = "Ostrze nieskonosci";
	private final static double DMG_1 = 80;
	
	private final static String NEXTDESC = "Predkosc poruszania zwiekszona o 20";
	private final static int NEXTLEVEL = 15;
	private final static double NEXTPOWER = 450;
	private final static boolean NEXTMAGIC = true;
	
	@Before 
	public void initialize() {
	
		articleManager.clearUniqueAbility();
		articleManager.clearArticle();
		
	}
	
	@Test
	public void checkConnection(){
		assertNotNull(articleManager.getConnection());
	}
	
	@Test
	public void checkAddingUniqueAbility(){
		articleManager.clearUniqueAbility();
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		articleManager.clearUniqueAbility();
		articleManager.clearArticle();
		
		assertEquals(1,articleManager.addUniqueAbility(ua));   //id = 0
		List<UniqueAbility> uas = articleManager.getAllUniqueAbility();
		UniqueAbility uasRetrieved = uas.get(0);
		
		assertEquals(DESC_1, uasRetrieved.getDesc());
		assertEquals(POWER_1, uasRetrieved.getPower(), 0.1);
		assertEquals(Magic_1, uasRetrieved.getMagic());
		assertEquals(LEVEL_1, uasRetrieved.getLevel());

	}
	
	@Test
	public void checkAddingArticle() {	
		
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		assertEquals(1,articleManager.addUniqueAbility(ua));   //id = 1
		
		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		
		Article article = new Article();
		article.setName(NAME_1);
		article.setDmg(DMG_1);
		article.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, article);   //id = 0
		
		Article artRetrived = abiRetrieved.getArticle().get(0);
//		System.out.println("t");
//		System.out.println(artRetrived.getName());
//		System.out.println(artRetrived.getDmg());
		
		assertEquals(NAME_1, artRetrived.getName());
		assertEquals(DMG_1, artRetrived.getDmg(), 0.1);
	}
	
	@Test
	public void ArticleGet() {
		
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		assertEquals(1,articleManager.addUniqueAbility(ua));   //id = 2

		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		
		Article article = new Article();
		article.setName(NAME_1);
		article.setDmg(DMG_1);
		article.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, article);     //id  = 1
		
		unis = articleManager.getAllUniqueAbility();
		abiRetrieved = unis.get(0);
		
		List<Article> art = articleManager.getAllArticle(abiRetrieved.getId());
		Article artRetrived = art.get(0);
		assertEquals(1, art.size());
		assertEquals(NAME_1, artRetrived.getName());
		assertEquals(DMG_1, artRetrived.getDmg(), 0.1);
	}
	
	@Test
	public void GetAllUniqueAbility(){
		
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 3

		ua.setDesc(NEXTDESC);
		ua.setPower(NEXTPOWER);
		ua.setMagic(NEXTMAGIC);
		ua.setLevel(NEXTLEVEL);
		
		assertEquals(1,articleManager.addUniqueAbility(ua)); //id = 4
		
		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		assertEquals(DESC_1, abiRetrieved.getDesc());
		abiRetrieved = unis.get(1);
		assertEquals(NEXTDESC, abiRetrieved.getDesc());
		
	}
	
	
	@Test
	public void EditUniqueAbility() {
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 5
		
		List<UniqueAbility> uniabi = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = uniabi.get(0);
		abiRetrieved.setDesc(NEXTDESC);
		abiRetrieved.setPower(NEXTPOWER);
		abiRetrieved.setLevel(NEXTLEVEL);
		abiRetrieved.setMagic(NEXTMAGIC);
		articleManager.editUniqueAbility(abiRetrieved.getId(), abiRetrieved);
		
		uniabi = articleManager.getAllUniqueAbility();
		UniqueAbility edited = uniabi.get(0);
		assertEquals(NEXTDESC, edited.getDesc());
		assertEquals(NEXTPOWER, edited.getPower(), 0.01);
		assertEquals(NEXTLEVEL, edited.getLevel());
		assertEquals(NEXTMAGIC, edited.getMagic());
	}

	@Test
	public void UniqueAbilityDelete(){
		
		UniqueAbility ua = new UniqueAbility();
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 6
		
		ua.setDesc(NEXTDESC);
		ua.setPower(NEXTPOWER);
		ua.setMagic(NEXTMAGIC);
		ua.setLevel(NEXTLEVEL);
		
		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 7
		
		List<UniqueAbility> abis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = abis.get(0);
		
		assertEquals(2, abis.size());
		
		Article accessory = new Article();
		accessory.setName(NAME_1);
		accessory.setDmg(DMG_1);
		accessory.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, accessory);   //id = 2

		abis = articleManager.getAllUniqueAbility();
		abiRetrieved = abis.get(0);

	
		articleManager.deleteUniqueAbility(abiRetrieved);
		abis = articleManager.getAllUniqueAbility();
	    

	    assertEquals(1, abis.size());
	    
	    UniqueAbility uniabi2 = abis.get(0);
		assertEquals(NEXTDESC, uniabi2.getDesc());
		assertEquals(NEXTPOWER, uniabi2.getPower(), 0.1);
		assertEquals(NEXTMAGIC, uniabi2.getMagic());
		assertEquals(NEXTLEVEL, uniabi2.getLevel());
		
	}
	
}

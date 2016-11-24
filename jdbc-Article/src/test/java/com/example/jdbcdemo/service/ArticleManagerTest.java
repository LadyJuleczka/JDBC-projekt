package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Article;
import com.example.jdbcdemo.domain.UniqueAbility;


public class ArticleManagerTest {
	
	
	ArticleManager articleManager = new ArticleManager();
	
	private final static String DESC_1 = "Obrazenia krytyczne zwiekszone o 20%";
	private final static int LEVEL_1 = 80000;
	private final static double POWER_1 = 200;
	private final static boolean Magic_1 = false;
	
	private final static String NAME_1 = "bag";
	private final static double DMG_1 = 69;
	
	private final static String NEXTDESC = "D200";
	private final static int NEXTLEVEL = 75000;
	private final static double NEXTPOWER = 450;
	private final static boolean NEXTMAGIC = true;
	
	@Before 
	public void initialize() {
		UniqueAbility abi = new UniqueAbility();
		abi.setDesc(DESC_1);
		abi.setPower(POWER_1);
		abi.setMagic(Magic_1);
		abi.setLevel(LEVEL_1);
		
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
		ua.setDesc(DESC_1);
		ua.setPower(POWER_1);
		ua.setMagic(Magic_1);
		ua.setLevel(LEVEL_1);
		
		articleManager.clearUniqueAbility();
		articleManager.clearArticle();
		
		assertEquals(1,articleManager.addUniqueAbility(ua));
		List<UniqueAbility> uas = articleManager.getAllUniqueAbility();
		UniqueAbility uasRetrieved = uas.get(0);
		
		assertEquals(DESC_1, uasRetrieved.getDesc());
		assertEquals(POWER_1, uasRetrieved.getPower(), 0.1);
		assertEquals(Magic_1, uasRetrieved.getMagic());
		assertEquals(LEVEL_1, uasRetrieved.getLevel());

	}
	
	@Test
	public void checkAddingSecond() {		
		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		
		Article article = new Article();
		article.setName(NAME_1);
		article.setDmg(DMG_1);
		article.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, article);
		
		Article artRetrived = abiRetrieved.getArticle().get(0);
//		System.out.println("t");
//		System.out.println(artRetrived.getName());
//		System.out.println(artRetrived.getDmg());
		
		assertEquals(NAME_1, artRetrived.getName());
		assertEquals(DMG_1, artRetrived.getDmg(), 0.1);
	}
	
	@Test
	public void ArticleGet() {

		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = unis.get(0);
		
		Article article = new Article();
		article.setName(NAME_1);
		article.setDmg(DMG_1);
		article.setUaId(abiRetrieved.getId());
		articleManager.addArticle(abiRetrieved, article);
		
		unis = articleManager.getAllUniqueAbility();
		abiRetrieved = unis.get(0);
		
		List<Article> art = articleManager.getAllArticle(abiRetrieved.getId());
		Article artRetrived = art.get(0);
		assertEquals(1, art.size());
		assertEquals(NAME_1, artRetrived.getName());
		assertEquals(DMG_1, artRetrived.getDmg(), 0.1);
	}
	
	@Test
	public void EditUniqueAbility() {
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
	public void CameraDelete(){

		UniqueAbility abi = new UniqueAbility();
		abi.setDesc(NEXTDESC);
		abi.setPower(NEXTPOWER);
		abi.setMagic(NEXTMAGIC);
		abi.setLevel(NEXTLEVEL);
		
		articleManager.addUniqueAbility(abi);
		
		List<UniqueAbility> abis = articleManager.getAllUniqueAbility();
		UniqueAbility abiRetrieved = abis.get(0);
		
		assertEquals(2, abis.size());
		
		Article accessory = new Article();
		accessory.setName(NAME_1);
		accessory.setDmg(DMG_1);
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
		assertEquals(NEXTDESC, uniabi2.getDesc());
		assertEquals(NEXTPOWER, uniabi2.getPower(), 0.1);
		assertEquals(NEXTMAGIC, uniabi2.getMagic());
		assertEquals(NEXTLEVEL, uniabi2.getLevel());
	}
}

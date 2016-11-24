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
	public void AccessoryGet() {

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
	
//	@Test
//	public void checkEditMain() {
//		List<Camera> cameras = cameraManager.getAllCameras();
//		Camera camerasRetrieved = cameras.get(0);
//		camerasRetrieved.setModel(NEWMODEL_1);
//		camerasRetrieved.setPrice(NEWPRICE_1);
//		camerasRetrieved.setShots(NEWSHOTS_1);
//		camerasRetrieved.setSold(NEWSOLD_1);
//		cameraManager.editCamera(camerasRetrieved.getId(), camerasRetrieved);
//		
//		cameras = cameraManager.getAllCameras();
//		Camera edited = cameras.get(0);
//		assertEquals(NEWMODEL_1, edited.getModel());
//		assertEquals(NEWPRICE_1, edited.getPrice(), 0.01);
//		assertEquals(NEWSHOTS_1, edited.getShots());
//		assertEquals(NEWSOLD_1, edited.getSold());
//	}
//
//	@Test
//	public void CameraDelete(){
//
//		Camera camera = new Camera();
//		camera.setModel(NEWMODEL_1);
//		camera.setPrice(NEWPRICE_1);
//		camera.setSold(NEWSOLD_1);
//		camera.setShots(NEWSHOTS_1);
//		
//		cameraManager.addCamera(camera);
//		
//		List<Camera> cameras = cameraManager.getAllCameras();
//		Camera camerasRetrieved = cameras.get(0);
//		
//		assertEquals(2, cameras.size());
//		
//		Accessory accessory = new Accessory();
//		accessory.setName(NAME_1);
//		accessory.setPrice(PRICE_2);
//		accessory.setOwnerId(camerasRetrieved.getId());
//		cameraManager.addAccessory(camerasRetrieved, accessory);
//
//		cameras = cameraManager.getAllCameras();
//		camerasRetrieved = cameras.get(0);
//
//	    // deleting the user
//	    cameraManager.deleteCamera(camerasRetrieved);
//	    cameras = cameraManager.getAllCameras();
//	    
//	    // checking deletion
//	    assertEquals(1, cameras.size());
//	    
//	    Camera otherCamera = cameras.get(0);
//		assertEquals(NEWMODEL_1, otherCamera.getModel());
//		assertEquals(NEWPRICE_1, otherCamera.getPrice(), 0.1);
//		assertEquals(NEWSOLD_1, otherCamera.getSold());
//		assertEquals(NEWSHOTS_1, otherCamera.getShots());
//	}
}

package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Article;
import com.example.jdbcdemo.domain.UniqueAbility;

public class ArticleManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableUniqueAbility = "CREATE TABLE "
			+ "UniqueAbility(id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, "
			+ "desc varchar(20), "
			+ "power double, "
			+ "magic smallint, "
			+ "level int);";

	private String createTableArticle = "CREATE TABLE "
			+ "Article(id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, "
			+ "name varchar(20), "
			+ "dmg double, "
			+ "ua_id int,"
			+ "FOREIGN KEY (ua_id) REFERENCES UniqueAbility(id));";
	
	private PreparedStatement addArticleStmt;
	private PreparedStatement deleteArticleStmt;
	private PreparedStatement getAllArticleStmt;
	private PreparedStatement deleteArticleOwnerStmt;	
	
	private PreparedStatement addUniqueAbilityStmt;	
	private PreparedStatement editUniqueAbilityStmt;
	private PreparedStatement deleteAllUniqueAbilityStmt;
	private PreparedStatement getAllUniqueAbilitiyStmt;	
	private PreparedStatement deleteUniqueAbilityStmt;


	private Statement statement;

	public ArticleManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs1 = connection.getMetaData().getTables(null, null, "UNIQUEABILITY",
					null);

			if (!rs1.next()) {
				statement.executeUpdate(createTableUniqueAbility);
			}
			ResultSet rs2 = connection.getMetaData().getTables(null, null, "ARTICLE",
					null);
	
			if (!rs2.next()) {
				statement.executeUpdate(createTableArticle);
			}
				
			
			addUniqueAbilityStmt = connection
					.prepareStatement("INSERT INTO UniqueAbility (desc, power, magic, level) VALUES (?, ?, ?, ?)");
			deleteAllUniqueAbilityStmt = connection
					.prepareStatement("DELETE FROM UniqueAbility");
			getAllUniqueAbilitiyStmt = connection
					.prepareStatement("SELECT * FROM UniqueAbility");
			editUniqueAbilityStmt = connection.prepareStatement("UPDATE UniqueAbility SET desc = ?, power = ?, magic = ?, level = ?"
					+ "WHERE id = ?");
			deleteUniqueAbilityStmt = connection.prepareStatement("DELETE FROM UniqueAbility WHERE id = ?");

			deleteArticleOwnerStmt = connection.prepareStatement("DELETE FROM Article WHERE ua_id = ?");
			addArticleStmt = connection
					.prepareStatement("INSERT INTO Article (name, dmg, ua_id) VALUES (?, ?, ?)");
			deleteArticleStmt = connection
					.prepareStatement("DELETE FROM Article");
			getAllArticleStmt = connection
					.prepareStatement("SELECT * FROM Article WHERE ua_id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}


	void clearArticle() {
		try {
			deleteArticleStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void clearUniqueAbility() {
		try {
			this.clearArticle();
			deleteAllUniqueAbilityStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addUniqueAbility(UniqueAbility abi) {
		int count = 0;
		try {
			addUniqueAbilityStmt.setString(1, abi.getDesc());
			addUniqueAbilityStmt.setDouble(2, abi.getPower());
			addUniqueAbilityStmt.setBoolean(3, abi.getMagic());
			addUniqueAbilityStmt.setInt(4, abi.getLevel());

			count = addUniqueAbilityStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
//	
//	public int editCamera(int id, Camera camera) {
//		int count=0;
//		try {
//			editCameraStmt.setInt(5, id);
//			editCameraStmt.setString(1, camera.getModel());
//			editCameraStmt.setDouble(2, camera.getPrice());
//			editCameraStmt.setBoolean(3, camera.getSold());
//			editCameraStmt.setInt(4, camera.getShots());
//			
//			
//			editCameraStmt.execute();
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return count;
//	}
//	
//
	public int addArticle(UniqueAbility uni, Article art) {
		int count = 0;
		try {
			addArticleStmt.setString(1, art.getName());
			addArticleStmt.setDouble(2, art.getDmg());
			addArticleStmt.setInt(3, uni.getId());
			uni.addArticle(art);
			addArticleStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Article> getAllArticle(int oa) {
		List<Article> art = new ArrayList<Article>();
		
		try {
			getAllArticleStmt.setInt(1, oa);
			ResultSet rs2 = getAllArticleStmt.executeQuery();
			while (rs2.next()) {
				Article x = new Article();
				x.setId(rs2.getInt("id"));
				x.setName(rs2.getString("name"));
				x.setDmg(rs2.getDouble("dmg"));
				x.setUaId(rs2.getInt("ua_id"));
				art.add(x);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return art;
	}
	
	public List<UniqueAbility> getAllUniqueAbility() {
		List<UniqueAbility> unis = new ArrayList<UniqueAbility>();

		try {
			ResultSet rs = getAllUniqueAbilitiyStmt.executeQuery();

			while (rs.next()) {
				UniqueAbility ua = new UniqueAbility();
				ua.setId(rs.getInt("id"));
				ua.setDesc(rs.getString("desc"));
				ua.setLevel(rs.getInt("level"));
				ua.setPower(rs.getDouble("power"));
				ua.setMagic(rs.getBoolean("magic"));
				
				getAllArticleStmt.setInt(1, ua.getId());
				ResultSet rs2 = getAllArticleStmt.executeQuery();
				while (rs2.next()) {
					Article x = new Article();
					x.setId(rs2.getInt("id"));
					x.setName(rs2.getString("name"));
					x.setDmg(rs2.getDouble("dmg"));
					x.setUaId(rs2.getInt("ua_id"));
					ua.addArticle(x);
				}
				unis.add(ua);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unis;
	}
//	
//	public int deleteCamera(Camera entity) {
//		int count = 0;
//		try {
//			deleteAccessoryOwnerStmt.setInt(1, entity.getId());
//			deleteCameraStmt.setInt(1, entity.getId());
//			deleteAccessoryOwnerStmt.execute();
//			deleteCameraStmt.execute();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return count;
//	}

}

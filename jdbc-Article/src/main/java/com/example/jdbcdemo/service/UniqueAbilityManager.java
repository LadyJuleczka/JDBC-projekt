package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.UniqueAbility;

public class UniqueAbilityManager {

	private Connection connection;

//	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableUniqueAbility = "CREATE TABLE UniqueAbility(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20), description varchar(80), PRIMARY KEY(id))";

	private PreparedStatement addUniqueAbilityStmt;
	private PreparedStatement deleteAllUniqueAbilitiesStmt;
	private PreparedStatement getAllUniqueAbilitiesStmt;
	private PreparedStatement SelectId;

	private Statement statement;

	public UniqueAbilityManager() {
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:file:/tmp/testdb;ifexists=false", "SA", "");
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("UniqueAbility".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableUniqueAbility);

			addUniqueAbilityStmt = connection.prepareStatement("INSERT INTO UniqueAbility (name, description) VALUES (?, ?)");
			deleteAllUniqueAbilitiesStmt = connection.prepareStatement("DELETE FROM UniqueAbility");
			getAllUniqueAbilitiesStmt = connection.prepareStatement("SELECT id, name, description FROM UniqueAbility");
			SelectId = connection.prepareStatement("SELECT id FROM UniqueAbility WHERE name=?;");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void clearUniqueAbilitiy() {
		try {
			deleteAllUniqueAbilitiesStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addUniqueAbility(UniqueAbility uniqueAbility) {
		int count = 0;
		try {
			addUniqueAbilityStmt.setString(1, uniqueAbility.getName());
			addUniqueAbilityStmt.setString(2, uniqueAbility.getDescription());

			count = addUniqueAbilityStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<UniqueAbility> getAllUniqueAbility() {
		List<UniqueAbility> uniqueAbilities = new ArrayList<UniqueAbility>();

		try {
			ResultSet rs = getAllUniqueAbilitiesStmt.executeQuery();

			while (rs.next()) {
				UniqueAbility ua = new UniqueAbility();
				ua.setId(rs.getInt("id"));
				ua.setName(rs.getString("name"));
				ua.setDescription(rs.getString("description"));
				uniqueAbilities.add(ua);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uniqueAbilities;
	}
	
	public int select_id_from_uniqueAbility(String name){
		int output = -1;
		try {
			SelectId.setString(1, name);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		try {
		ResultSet rs = SelectId.executeQuery();
		while (rs.next()) {
		output = rs.getInt("id");
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return output;
		}
	
	

}

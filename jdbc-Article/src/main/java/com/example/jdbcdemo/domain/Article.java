package com.example.jdbcdemo.domain;

public class Article {

	private long id;

	private String name;
	private int dmg;
	private String type;
	private int uniqueAbility_id;

	public Article() {
	}

	public Article(String name, int dmg, String type) {
		super();
		this.name = name;
		this.dmg = dmg;
		this.type = type;
	}

	public Article(String name, int dmg, String type, int uniqueAbility_id) {
		super();
		this.name = name;
		this.dmg = dmg;
		this.type = type;
		this.uniqueAbility_id = uniqueAbility_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int yob) {
		this.dmg = yob;
	}

	public int getUniqueAbility_id() {
		return uniqueAbility_id;
	}

	public void setUniqueAbility_id(int uniqueAbility_id) {
		this.uniqueAbility_id = uniqueAbility_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

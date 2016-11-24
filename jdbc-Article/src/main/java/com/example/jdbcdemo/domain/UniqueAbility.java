package com.example.jdbcdemo.domain;
import java.util.ArrayList;

import com.example.jdbcdemo.domain.Article;

public class UniqueAbility {
	
	private int id;
	
	private String name;
	private String description;
	private ArrayList<Article> articles = new ArrayList<Article>();
	
	public UniqueAbility() {
	}
	
	public UniqueAbility(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addAccessory(Article input) {
		this.articles.add(input);
	}
	
	public ArrayList<Article> getAccessory() {
		return this.articles;
	}
	
}

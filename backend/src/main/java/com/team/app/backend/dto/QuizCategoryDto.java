package com.team.app.backend.dto;

public class QuizCategoryDto {
	private String title;
	private String[] categories;
	private int dateOption;
	private String user;
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String[] getCategories() {
		return this.categories;
	}
	
	public int getDateOption() {
		return this.dateOption;
	}
	
	public void setDateOption(int dateOption) {
		this.dateOption = dateOption;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
}
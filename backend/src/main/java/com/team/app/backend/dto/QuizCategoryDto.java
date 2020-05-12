package com.team.app.backend.dto;

public class QuizCategoryDto {
	private String title;
	private String[] categories;
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String[] getCategories() {
		return this.categories;
	}
}
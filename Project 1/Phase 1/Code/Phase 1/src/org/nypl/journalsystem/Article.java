package org.nypl.journalsystem;

import java.util.List;

import org.nypl.journalsystem.core.IArticle;

public class Article implements IArticle{
	
	private String title;
	private List<Author> authors;
	
	public Article(String title, List<Author> authors) {
		
		this.title = title;
		this.authors = authors;
		
	}
	

	public List<Author> getAuthors() {
		return authors;
	}

	public String getTitle() {
		return title;
	}

}

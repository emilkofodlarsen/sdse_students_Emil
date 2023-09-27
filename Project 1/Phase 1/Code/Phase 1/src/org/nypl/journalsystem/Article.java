package org.nypl.journalsystem;

import java.util.List;

public class Article {
	
	private String title;
	private List<Author> authors;
	
	public Article(String title, List<Author> authors) {
		
		this.title = title;
		this.authors = authors;
		
	}
	
	public void display() {
		for (Author author : authors) {
			System.out.println("article");
			author.display();
			
		}
	}
	
	public String toString() {
		return title;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}

}

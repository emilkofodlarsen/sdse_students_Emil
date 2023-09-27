package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.List;

public class Journal {
	private String name;
	private Publisher publisher;
	private String issn;
	private List<Article> articles = new ArrayList<Article>();
	private boolean fullIssue = false;
	
	public Journal(String name, Publisher publisher, String issn) {
		this.name = name;
		this.publisher = publisher;
		this.issn = issn;
	}
	
	public void addArticle(Article article) {
		articles.add(article);
		if (articles.size() >= 3) {
			fullIssue = true;
		}
	}
	
	public boolean fullIssue() {
		return fullIssue;
	}
	
	public String toString() {
		return name;
	}
	public void display() {
		
		String fullIssueText ="";
		if (fullIssue) {
			fullIssueText = " *FULL ISSUE*";
		} 
		System.out.println("Journal: \"" + name + "\" Published by: " + publisher + " ISSN:" +issn + fullIssueText);
		for (Article article : articles) {
			System.out.println("	"+article + " by:");
			for (Author author : article.getAuthors()) {
				System.out.println("		"+author);
			}
		}
	}
	
	

}

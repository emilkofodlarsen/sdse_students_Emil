package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.nypl.journalsystem.core.IArticle;
import org.nypl.journalsystem.core.IJournal;
import org.nypl.journalsystem.core.IPublisher;

public class Journal implements IJournal{
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
	
	public boolean isFullIssue() {
		return fullIssue;
	}
	
	public String getName() {
		return name;
	}
	public void display() {
		
		String fullIssueText ="";
		if (fullIssue) {
			fullIssueText = " *FULL ISSUE*";
		} 
		System.out.println("Journal: \"" + name + "\" Published by: " + publisher.getName() + " ISSN:" +issn + fullIssueText);
		for (Article article : articles) {
			System.out.println("	"+article.getTitle() + " by:");
			for (Author author : article.getAuthors()) {
				System.out.println("		"+author.getName());
			}
		}
	}

	
	public Collection<Article> getArticles() {
		return articles;
	}

	
	public String getIssn() {
		return issn;
	}

	
	public Publisher getPublisher() {
		return publisher;
	}

	
	
	

}

package org.nypl.journalsystem;


import org.nypl.journalsystem.core.IAuthor;

public class Author implements IAuthor{
	
	private int id;
	private String name;
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	


}

package com.tutorialapp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "tutorials") // this helps us writing the name of collection in mongodb to "tutorials"

public class Tutorial {
	@Id
	private String id;
	
	private String title;
	private String description;
	private boolean published;
	
	public Tutorial() {
		
	}
	public Tutorial(String title, String description, boolean published) {
		this.title=title;
		this.description=description;
		this.published=published;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public boolean isPublished() {
		return published;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setPublished(boolean published) {
		this.published=published;
	}
	
	@Override
	public String toString() {
		return "Tutorial [id ="+ id+", title ="+title+",desc ="+description+", published ="+published+"]";
	}
	
	

}

package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Tags {
 
	private String tags; 

	public Tags() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Tags : "
		+ " tags = " + tags;
		return s;
	} 
} 

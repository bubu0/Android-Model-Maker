package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class TagsJson { 
	@JsonProperty("tags")
	private String tags; 

	public TagsJson(String tags) { 
		super();
		this.tags = tags;
	} 

	public TagsJson() {
		super();
	} 
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

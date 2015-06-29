package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class ObjectsJson { 
	@JsonProperty("objects")
	private String objects; 

	public ObjectsJson(String objects) { 
		super();
		this.objects = objects;
	} 

	public ObjectsJson() {
		super();
	} 
	public String getObjects() {
		return this.objects;
	}

	public void setObjects(String objects) {
		this.objects = objects;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Objects : "
		+ " objects = " + objects;
		return s;
	} 
} 

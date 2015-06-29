package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Objects {
 
	private String objects; 

	public Objects() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
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

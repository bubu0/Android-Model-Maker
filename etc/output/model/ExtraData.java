package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class ExtraData {
 
	private String display; 

	public ExtraData() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
 
	@Override
	public String toString() {
		final String s = "Object : ExtraData : "
		+ " display = " + display;
		return s;
	} 
} 

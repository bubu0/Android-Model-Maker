package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class AvailableTransitions {
 
	private String availableTransitions; 

	public AvailableTransitions() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public String getAvailableTransitions() {
		return this.availableTransitions;
	}

	public void setAvailableTransitions(String availableTransitions) {
		this.availableTransitions = availableTransitions;
	}
 
	@Override
	public String toString() {
		final String s = "Object : AvailableTransitions : "
		+ " availableTransitions = " + availableTransitions;
		return s;
	} 
} 

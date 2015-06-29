package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class AvailableTransitionsJson { 
	@JsonProperty("available_transitions")
	private String availableTransitions; 

	public AvailableTransitionsJson(String availableTransitions) { 
		super();
		this.availableTransitions = availableTransitions;
	} 

	public AvailableTransitionsJson() {
		super();
	} 
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

package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class ExtraDataJson { 
	@JsonProperty("display")
	private String display; 

	public ExtraDataJson(String display) { 
		super();
		this.display = display;
	} 

	public ExtraDataJson() {
		super();
	} 
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

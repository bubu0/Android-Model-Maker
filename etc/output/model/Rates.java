package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Rates {
 
	private String rate; 
	private long countDb; 

	public Rates() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
 
	public long getCountDb() {
		return this.countDb;
	}

	public void setCountDb(long countDb) {
		this.countDb = countDb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Rates : "
		+ " rate = " + rate
		+ " countDb = " + countDb;
		return s;
	} 
} 

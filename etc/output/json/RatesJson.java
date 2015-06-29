package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class RatesJson { 
	@JsonProperty("rate")
	private String rate; 
	@JsonProperty("count")
	private long countDb; 

	public RatesJson(String rate, long countDb) { 
		super();
		this.rate = rate;
		this.countDb = countDb;
	} 

	public RatesJson() {
		super();
	} 
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

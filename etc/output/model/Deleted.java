package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Deleted {
 
	private Long deleted; 

	public Deleted() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public Long getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Long deleted) {
		this.deleted = deleted;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Deleted : "
		+ " deleted = " + deleted;
		return s;
	} 
} 

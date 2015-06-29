package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class DeletedJson { 
	@JsonProperty("deleted")
	private Long deleted; 

	public DeletedJson(Long deleted) { 
		super();
		this.deleted = deleted;
	} 

	public DeletedJson() {
		super();
	} 
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

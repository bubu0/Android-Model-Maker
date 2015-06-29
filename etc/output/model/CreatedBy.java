package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class CreatedBy {
 
	private String username; 
	//URI to users
	private String resourceUri; 
	private long idDb; 

	public CreatedBy() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : CreatedBy : "
		+ " username = " + username
		+ " resourceUri = " + resourceUri
		+ " idDb = " + idDb;
		return s;
	} 
} 

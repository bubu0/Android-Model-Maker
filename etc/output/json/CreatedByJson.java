package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class CreatedByJson { 
	@JsonProperty("username")
	private String username; 
	//URI to users
	@JsonProperty("resource_uri")
	private String resourceUri; 
	@JsonProperty("id")
	private long idDb; 

	public CreatedByJson(String username, String resourceUri, long idDb) { 
		super();
		this.username = username;
		this.resourceUri = resourceUri;
		this.idDb = idDb;
	} 

	public CreatedByJson() {
		super();
	} 
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

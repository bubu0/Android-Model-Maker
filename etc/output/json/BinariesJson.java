package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class BinariesJson { 

	//URI to appliances
	@JsonProperty("appliance")
	private String appliance; 

	@JsonProperty("checksum")
	private String checksum; 

	@JsonProperty("created")
	private Date created; 

	@JsonProperty("created_by")
	private String createdBy; 

	@JsonProperty("id")
	private long idDb; 

	//URI to recipes
	@JsonProperty("linked_resource")
	private String linkedResource; 

	@JsonProperty("modified")
	private Date modified; 

	@JsonProperty("modified_by")
	private String modifiedBy; 

	@JsonProperty("recipes")
	private RecipesJson recipes; 

	@JsonProperty("state")
	private String state; 

	@JsonProperty("url")
	private String url; 

	@JsonProperty("version")
	private String version; 

	public BinariesJson(String appliance, String checksum, Date created, String createdBy, long idDb, String linkedResource, Date modified, String modifiedBy, RecipesJson recipes, String state, String url, String version) { 
		super();
		this.appliance = appliance;
		this.checksum = checksum;
		this.created = created;
		this.createdBy = createdBy;
		this.idDb = idDb;
		this.linkedResource = linkedResource;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
		this.recipes = recipes;
		this.state = state;
		this.url = url;
		this.version = version;
	} 

	public BinariesJson() {
		super();
	} 
	public String getAppliance() {
		return this.appliance;
	}

	public void setAppliance(String appliance) {
		this.appliance = appliance;
	}
 
	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
 
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
 
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	public String getLinkedResource() {
		return this.linkedResource;
	}

	public void setLinkedResource(String linkedResource) {
		this.linkedResource = linkedResource;
	}
 
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
 
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
 
	public RecipesJson getRecipes() {
		return this.recipes;
	}

	public void setRecipes(RecipesJson recipes) {
		this.recipes = recipes;
	}
 
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
 
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
 
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Binaries : "
		+ " appliance = " + appliance
		+ " checksum = " + checksum
		+ " created = " + created
		+ " createdBy = " + createdBy
		+ " idDb = " + idDb
		+ " linkedResource = " + linkedResource
		+ " modified = " + modified
		+ " modifiedBy = " + modifiedBy
		+ " recipes = " + recipes
		+ " state = " + state
		+ " url = " + url
		+ " version = " + version;
		return s;
	} 
} 

package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class NutritionsJson { 
	@JsonProperty("created")
	private Date created; 
	@JsonProperty("modified")
	private Date modified; 
	@JsonProperty("type")
	private String type; 
	//URI to users
	@JsonProperty("created_by")
	private String createdBy; 
	//URI to units
	@JsonProperty("unit")
	private String unit; 
	//URI to users
	@JsonProperty("modified_by")
	private String modifiedBy; 
	//URI to nutritions
	@JsonProperty("resource_uri")
	private String resourceUri; 
	//URI to variants
	@JsonProperty("variant")
	private String variant; 
	@JsonProperty("value")
	private double value; 
	@JsonProperty("id")
	private long idDb; 

	public NutritionsJson(Date created, Date modified, String type, String createdBy, String unit, String modifiedBy, String resourceUri, String variant, double value, long idDb) { 
		super();
		this.created = created;
		this.modified = modified;
		this.type = type;
		this.createdBy = createdBy;
		this.unit = unit;
		this.modifiedBy = modifiedBy;
		this.resourceUri = resourceUri;
		this.variant = variant;
		this.value = value;
		this.idDb = idDb;
	} 

	public NutritionsJson() {
		super();
	} 
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
 
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
 
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
 
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public String getVariant() {
		return this.variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}
 
	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Nutritions : "
		+ " created = " + created
		+ " modified = " + modified
		+ " type = " + type
		+ " createdBy = " + createdBy
		+ " unit = " + unit
		+ " modifiedBy = " + modifiedBy
		+ " resourceUri = " + resourceUri
		+ " variant = " + variant
		+ " value = " + value
		+ " idDb = " + idDb;
		return s;
	} 
} 

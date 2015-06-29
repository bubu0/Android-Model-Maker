package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Nutritions {
 
	private Date created; 
	private Date modified; 
	private String type; 
	//URI to users
	private String createdBy; 
	//URI to units
	private String unit; 
	//URI to users
	private String modifiedBy; 
	//URI to nutritions
	private String resourceUri; 
	//URI to variants
	private String variant; 
	private double value; 
	private long idDb; 

	public Nutritions() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
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

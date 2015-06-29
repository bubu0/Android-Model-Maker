package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Parameters {
 
	private Date created; 
	private Date modified; 
	private String type; 
	//URI to users
	private String createdBy; 
	//URI to users
	private String modifiedBy; 
	//URI to actions
	private String action; 
	//URI to action_parameters
	private String resourceUri; 
	//URI to units
	private String unit; 
	private double value; 
	private long idDb; 

	public Parameters() {
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
 
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
 
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
		final String s = "Object : Parameters : "
		+ " created = " + created
		+ " modified = " + modified
		+ " type = " + type
		+ " createdBy = " + createdBy
		+ " modifiedBy = " + modifiedBy
		+ " action = " + action
		+ " resourceUri = " + resourceUri
		+ " unit = " + unit
		+ " value = " + value
		+ " idDb = " + idDb;
		return s;
	} 
} 

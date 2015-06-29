package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class ActionsJson { 
	@JsonProperty("action_type")
	private ActionTypeJson actionType; 
	@JsonProperty("parameters")
	private ArrayList<ParametersJson> parameters; 
	//URIs to cooking_steps
	@JsonProperty("cooking_steps")
	private ArrayList<String> cookingSteps; 
	@JsonProperty("modified")
	private Date modified; 
	@JsonProperty("created")
	private Date created; 
	@JsonProperty("action_display")
	private String actionDisplay; 
	//URI to users
	@JsonProperty("created_by")
	private String createdBy; 
	//URI to users
	@JsonProperty("modified_by")
	private String modifiedBy; 
	//URI to actions
	@JsonProperty("resource_uri")
	private String resourceUri; 
	//URI to steps
	@JsonProperty("step")
	private String step; 
	@JsonProperty("is_default")
	private boolean isDefault; 
	@JsonProperty("id")
	private long idDb; 
	@JsonProperty("order")
	private long orderDb; 

	public ActionsJson(ActionTypeJson actionType, ArrayList<ParametersJson> parameters, ArrayList<String> cookingSteps, Date modified, Date created, String actionDisplay, String createdBy, String modifiedBy, String resourceUri, String step, boolean isDefault, long idDb, long orderDb) { 
		super();
		this.actionType = actionType;
		this.parameters = parameters;
		this.cookingSteps = cookingSteps;
		this.modified = modified;
		this.created = created;
		this.actionDisplay = actionDisplay;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.resourceUri = resourceUri;
		this.step = step;
		this.isDefault = isDefault;
		this.idDb = idDb;
		this.orderDb = orderDb;
	} 

	public ActionsJson() {
		super();
	} 
	public ActionTypeJson getActionType() {
		return this.actionType;
	}

	public void setActionType(ActionTypeJson actionType) {
		this.actionType = actionType;
	}
 
	public ArrayList<ParametersJson> getParameters() {
		return this.parameters;
	}

	public void setParameters(ArrayList<ParametersJson> parameters) {
		this.parameters = parameters;
	}
 
	public ArrayList<String> getCookingSteps() {
		return this.cookingSteps;
	}

	public void setCookingSteps(ArrayList<String> cookingSteps) {
		this.cookingSteps = cookingSteps;
	}
 
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
 
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
 
	public String getActionDisplay() {
		return this.actionDisplay;
	}

	public void setActionDisplay(String actionDisplay) {
		this.actionDisplay = actionDisplay;
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
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public String getStep() {
		return this.step;
	}

	public void setStep(String step) {
		this.step = step;
	}
 
	public boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	public long getOrderDb() {
		return this.orderDb;
	}

	public void setOrderDb(long orderDb) {
		this.orderDb = orderDb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Actions : "
		+ " actionType = " + actionType
		+ " parameters = " + parameters
		+ " cookingSteps = " + cookingSteps
		+ " modified = " + modified
		+ " created = " + created
		+ " actionDisplay = " + actionDisplay
		+ " createdBy = " + createdBy
		+ " modifiedBy = " + modifiedBy
		+ " resourceUri = " + resourceUri
		+ " step = " + step
		+ " isDefault = " + isDefault
		+ " idDb = " + idDb
		+ " orderDb = " + orderDb;
		return s;
	} 
} 

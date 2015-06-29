package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Action {
 
	private ActionType actionType; 
	private ArrayList<Parameters> parameters; 
	//URIs to cooking_steps
	private ArrayList<String> cookingSteps; 
	private Date created; 
	private Date modified; 
	private String actionDisplay; 
	//URI to users
	private String createdBy; 
	//URI to users
	private String modifiedBy; 
	//URI to actions
	private String resourceUri; 
	//URI to steps
	private String step; 
	private boolean isDefault; 
	private long idDb; 
	private long orderDb; 

	public Action() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public ActionType getActionType() {
		return this.actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
 
	public ArrayList<Parameters> getParameters() {
		return this.parameters;
	}

	public void setParameters(ArrayList<Parameters> parameters) {
		this.parameters = parameters;
	}
 
	public ArrayList<String> getCookingSteps() {
		return this.cookingSteps;
	}

	public void setCookingSteps(ArrayList<String> cookingSteps) {
		this.cookingSteps = cookingSteps;
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
		final String s = "Object : Action : "
		+ " actionType = " + actionType
		+ " parameters = " + parameters
		+ " cookingSteps = " + cookingSteps
		+ " created = " + created
		+ " modified = " + modified
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

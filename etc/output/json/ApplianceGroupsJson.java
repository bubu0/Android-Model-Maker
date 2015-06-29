package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class ApplianceGroupsJson { 

	@JsonProperty("action_type")
	private ActionTypeJson actionType; 

	public ApplianceGroupsJson(ActionTypeJson actionType) { 
		super();
		this.actionType = actionType;
	} 

	public ApplianceGroupsJson() {
		super();
	} 
	public ActionTypeJson getActionType() {
		return this.actionType;
	}

	public void setActionType(ActionTypeJson actionType) {
		this.actionType = actionType;
	}
 
	@Override
	public String toString() {
		final String s = "Object : ApplianceGroups : "
		+ " actionType = " + actionType;
		return s;
	} 
} 

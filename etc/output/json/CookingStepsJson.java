package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class CookingStepsJson { 

	@JsonProperty("actions")
	private ActionsJson actions; 

	//URI to actions
	@JsonProperty("cooking_steps")
	private String cookingSteps; 

	public CookingStepsJson(ActionsJson actions, String cookingSteps) { 
		super();
		this.actions = actions;
		this.cookingSteps = cookingSteps;
	} 

	public CookingStepsJson() {
		super();
	} 
	public ActionsJson getActions() {
		return this.actions;
	}

	public void setActions(ActionsJson actions) {
		this.actions = actions;
	}
 
	public String getCookingSteps() {
		return this.cookingSteps;
	}

	public void setCookingSteps(String cookingSteps) {
		this.cookingSteps = cookingSteps;
	}
 
	@Override
	public String toString() {
		final String s = "Object : CookingSteps : "
		+ " actions = " + actions
		+ " cookingSteps = " + cookingSteps;
		return s;
	} 
} 

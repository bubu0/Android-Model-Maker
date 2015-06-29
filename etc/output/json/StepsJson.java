package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class StepsJson { 
	@JsonProperty("action")
	private ActionJson action; 
	@JsonProperty("actions")
	private ArrayList<ActionsJson> actions; 
	@JsonProperty("assigned_ingredients")
	private ArrayList<AssignedIngredientsJson> assignedIngredients; 
	@JsonProperty("desc_fr")
	private String descFr; 
	@JsonProperty("action_display")
	private String actionDisplay; 
	@JsonProperty("desc_template")
	private String descTemplate; 
	@JsonProperty("destination")
	private String destination; 
	@JsonProperty("desc_en")
	private String descEn; 
	@JsonProperty("desc_de")
	private String descDe; 
	@JsonProperty("desc_nl")
	private String descNl; 
	//URI to steps
	@JsonProperty("resource_uri")
	private String resourceUri; 
	//URI to variants
	@JsonProperty("variant")
	private String variant; 
	@JsonProperty("simple")
	private boolean simple; 
	@JsonProperty("id")
	private long idDb; 
	@JsonProperty("order")
	private long orderDb; 

	public StepsJson(ActionJson action, ArrayList<ActionsJson> actions, ArrayList<AssignedIngredientsJson> assignedIngredients, String descFr, String actionDisplay, String descTemplate, String destination, String descEn, String descDe, String descNl, String resourceUri, String variant, boolean simple, long idDb, long orderDb) { 
		super();
		this.action = action;
		this.actions = actions;
		this.assignedIngredients = assignedIngredients;
		this.descFr = descFr;
		this.actionDisplay = actionDisplay;
		this.descTemplate = descTemplate;
		this.destination = destination;
		this.descEn = descEn;
		this.descDe = descDe;
		this.descNl = descNl;
		this.resourceUri = resourceUri;
		this.variant = variant;
		this.simple = simple;
		this.idDb = idDb;
		this.orderDb = orderDb;
	} 

	public StepsJson() {
		super();
	} 
	public ActionJson getAction() {
		return this.action;
	}

	public void setAction(ActionJson action) {
		this.action = action;
	}
 
	public ArrayList<ActionsJson> getActions() {
		return this.actions;
	}

	public void setActions(ArrayList<ActionsJson> actions) {
		this.actions = actions;
	}
 
	public ArrayList<AssignedIngredientsJson> getAssignedIngredients() {
		return this.assignedIngredients;
	}

	public void setAssignedIngredients(ArrayList<AssignedIngredientsJson> assignedIngredients) {
		this.assignedIngredients = assignedIngredients;
	}
 
	public String getDescFr() {
		return this.descFr;
	}

	public void setDescFr(String descFr) {
		this.descFr = descFr;
	}
 
	public String getActionDisplay() {
		return this.actionDisplay;
	}

	public void setActionDisplay(String actionDisplay) {
		this.actionDisplay = actionDisplay;
	}
 
	public String getDescTemplate() {
		return this.descTemplate;
	}

	public void setDescTemplate(String descTemplate) {
		this.descTemplate = descTemplate;
	}
 
	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
 
	public String getDescEn() {
		return this.descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}
 
	public String getDescDe() {
		return this.descDe;
	}

	public void setDescDe(String descDe) {
		this.descDe = descDe;
	}
 
	public String getDescNl() {
		return this.descNl;
	}

	public void setDescNl(String descNl) {
		this.descNl = descNl;
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
 
	public boolean getSimple() {
		return this.simple;
	}

	public void setSimple(boolean simple) {
		this.simple = simple;
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
		final String s = "Object : Steps : "
		+ " action = " + action
		+ " actions = " + actions
		+ " assignedIngredients = " + assignedIngredients
		+ " descFr = " + descFr
		+ " actionDisplay = " + actionDisplay
		+ " descTemplate = " + descTemplate
		+ " destination = " + destination
		+ " descEn = " + descEn
		+ " descDe = " + descDe
		+ " descNl = " + descNl
		+ " resourceUri = " + resourceUri
		+ " variant = " + variant
		+ " simple = " + simple
		+ " idDb = " + idDb
		+ " orderDb = " + orderDb;
		return s;
	} 
} 

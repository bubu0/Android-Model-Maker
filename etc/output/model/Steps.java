package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Steps {
 
	private Action action; 
	private ArrayList<Actions> actions; 
	private ArrayList<AssignedIngredients> assignedIngredients; 
	private String descFr; 
	private String actionDisplay; 
	private String descTemplate; 
	private String destination; 
	private String descEn; 
	private String descDe; 
	private String descNl; 
	//URI to steps
	private String resourceUri; 
	//URI to variants
	private String variant; 
	private boolean simple; 
	private long idDb; 
	private long orderDb; 

	public Steps() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
 
	public ArrayList<Actions> getActions() {
		return this.actions;
	}

	public void setActions(ArrayList<Actions> actions) {
		this.actions = actions;
	}
 
	public ArrayList<AssignedIngredients> getAssignedIngredients() {
		return this.assignedIngredients;
	}

	public void setAssignedIngredients(ArrayList<AssignedIngredients> assignedIngredients) {
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

package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class ActionType {
 
	private ArrayList<AvailableTransitions> availableTransitions; 
	//URIs to terms
	private ArrayList<String> terms; 
	//URIs to appliance_groups
	private ArrayList<String> applianceGroups; 
	//URIs to medias
	private ArrayList<String> medias; 
	private CreatedBy createdBy; 
	private Date modified; 
	private Date created; 
	private ExtraData extraData; 
	private String state; 
	private String lang; 
	private String name; 
	//URI to appliance_groups
	private String applianceGroup; 
	//URI to markets
	private String market; 
	//URI to users
	private String modifiedBy; 
	//URI to action_types
	private String resourceUri; 
	private boolean isUseraction; 
	private long idDb; 

	public ActionType() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public ArrayList<AvailableTransitions> getAvailableTransitions() {
		return this.availableTransitions;
	}

	public void setAvailableTransitions(ArrayList<AvailableTransitions> availableTransitions) {
		this.availableTransitions = availableTransitions;
	}
 
	public ArrayList<String> getTerms() {
		return this.terms;
	}

	public void setTerms(ArrayList<String> terms) {
		this.terms = terms;
	}
 
	public ArrayList<String> getApplianceGroups() {
		return this.applianceGroups;
	}

	public void setApplianceGroups(ArrayList<String> applianceGroups) {
		this.applianceGroups = applianceGroups;
	}
 
	public ArrayList<String> getMedias() {
		return this.medias;
	}

	public void setMedias(ArrayList<String> medias) {
		this.medias = medias;
	}
 
	public CreatedBy getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
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
 
	public ExtraData getExtraData() {
		return this.extraData;
	}

	public void setExtraData(ExtraData extraData) {
		this.extraData = extraData;
	}
 
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
 
	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	public String getApplianceGroup() {
		return this.applianceGroup;
	}

	public void setApplianceGroup(String applianceGroup) {
		this.applianceGroup = applianceGroup;
	}
 
	public String getMarket() {
		return this.market;
	}

	public void setMarket(String market) {
		this.market = market;
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
 
	public boolean getIsUseraction() {
		return this.isUseraction;
	}

	public void setIsUseraction(boolean isUseraction) {
		this.isUseraction = isUseraction;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : ActionType : "
		+ " availableTransitions = " + availableTransitions
		+ " terms = " + terms
		+ " applianceGroups = " + applianceGroups
		+ " medias = " + medias
		+ " createdBy = " + createdBy
		+ " modified = " + modified
		+ " created = " + created
		+ " extraData = " + extraData
		+ " state = " + state
		+ " lang = " + lang
		+ " name = " + name
		+ " applianceGroup = " + applianceGroup
		+ " market = " + market
		+ " modifiedBy = " + modifiedBy
		+ " resourceUri = " + resourceUri
		+ " isUseraction = " + isUseraction
		+ " idDb = " + idDb;
		return s;
	} 
} 

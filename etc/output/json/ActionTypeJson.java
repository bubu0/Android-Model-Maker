package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class ActionTypeJson { 
	@JsonProperty("available_transitions")
	private ArrayList<AvailableTransitionsJson> availableTransitions; 
	//URIs to terms
	@JsonProperty("terms")
	private ArrayList<String> terms; 
	//URIs to appliance_groups
	@JsonProperty("appliance_groups")
	private ArrayList<String> applianceGroups; 
	//URIs to medias
	@JsonProperty("medias")
	private ArrayList<String> medias; 
	@JsonProperty("created_by")
	private CreatedByJson createdBy; 
	@JsonProperty("modified")
	private Date modified; 
	@JsonProperty("created")
	private Date created; 
	@JsonProperty("extra_data")
	private ExtraDataJson extraData; 
	@JsonProperty("state")
	private String state; 
	@JsonProperty("lang")
	private String lang; 
	@JsonProperty("name")
	private String name; 
	//URI to appliance_groups
	@JsonProperty("appliance_group")
	private String applianceGroup; 
	//URI to markets
	@JsonProperty("market")
	private String market; 
	//URI to users
	@JsonProperty("modified_by")
	private String modifiedBy; 
	//URI to action_types
	@JsonProperty("resource_uri")
	private String resourceUri; 
	@JsonProperty("is_useraction")
	private boolean isUseraction; 
	@JsonProperty("id")
	private long idDb; 

	public ActionTypeJson(ArrayList<AvailableTransitionsJson> availableTransitions, ArrayList<String> terms, ArrayList<String> applianceGroups, ArrayList<String> medias, CreatedByJson createdBy, Date modified, Date created, ExtraDataJson extraData, String state, String lang, String name, String applianceGroup, String market, String modifiedBy, String resourceUri, boolean isUseraction, long idDb) { 
		super();
		this.availableTransitions = availableTransitions;
		this.terms = terms;
		this.applianceGroups = applianceGroups;
		this.medias = medias;
		this.createdBy = createdBy;
		this.modified = modified;
		this.created = created;
		this.extraData = extraData;
		this.state = state;
		this.lang = lang;
		this.name = name;
		this.applianceGroup = applianceGroup;
		this.market = market;
		this.modifiedBy = modifiedBy;
		this.resourceUri = resourceUri;
		this.isUseraction = isUseraction;
		this.idDb = idDb;
	} 

	public ActionTypeJson() {
		super();
	} 
	public ArrayList<AvailableTransitionsJson> getAvailableTransitions() {
		return this.availableTransitions;
	}

	public void setAvailableTransitions(ArrayList<AvailableTransitionsJson> availableTransitions) {
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
 
	public CreatedByJson getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(CreatedByJson createdBy) {
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
 
	public ExtraDataJson getExtraData() {
		return this.extraData;
	}

	public void setExtraData(ExtraDataJson extraData) {
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

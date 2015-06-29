package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class EsRootJson { 

	//URIs to appliances
	@JsonProperty("appliances")	private ArrayList<String> appliances; 

	@JsonProperty("created")	private Date created; 

	//URI to users
	@JsonProperty("created_by")	private String createdBy; 

	@JsonProperty("desc")	private String desc; 

	@JsonProperty("difficulty")	private long difficulty; 

	@JsonProperty("food_cooking")	private boolean foodCooking; 

	@JsonProperty("id")	private long idDb; 

	@JsonProperty("lang")	private String lang; 

	//URI to markets
	@JsonProperty("market")	private String market; 

	//URIs to medias
	@JsonProperty("medias")	private ArrayList<String> medias; 

	@JsonProperty("modified")	private Date modified; 

	//URI to users
	@JsonProperty("modified_by")	private String modifiedBy; 

	@JsonProperty("name")	private String name; 

	//URIs to packs
	@JsonProperty("packs")	private ArrayList<String> packs; 

	@JsonProperty("rates")	private RatesJson rates; 

	//URI to recipes
	@JsonProperty("resource_uri")	private String resourceUri; 

	@JsonProperty("short_name")	private String shortName; 

	@JsonProperty("source_id")	private String sourceId; 

	@JsonProperty("source_uri")	private String sourceUri; 

	@JsonProperty("state")	private String state; 

	@JsonProperty("tags")	private ArrayList<TagsJson> tags; 

	//URIs to terms
	@JsonProperty("terms")	private ArrayList<String> terms; 

	@JsonProperty("tips")	private String tips; 

	@JsonProperty("variants")	private ArrayList<VariantsJson> variants; 

	@JsonProperty("version")	private String version; 

	public EsRootJson(ArrayList<String> appliances, Date created, String createdBy, String desc, long difficulty, boolean foodCooking, long idDb, String lang, String market, ArrayList<String> medias, Date modified, String modifiedBy, String name, ArrayList<String> packs, RatesJson rates, String resourceUri, String shortName, String sourceId, String sourceUri, String state, ArrayList<TagsJson> tags, ArrayList<String> terms, String tips, ArrayList<VariantsJson> variants, String version) { 
		super();
		this.appliances = appliances;
		this.created = created;
		this.createdBy = createdBy;
		this.desc = desc;
		this.difficulty = difficulty;
		this.foodCooking = foodCooking;
		this.idDb = idDb;
		this.lang = lang;
		this.market = market;
		this.medias = medias;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
		this.name = name;
		this.packs = packs;
		this.rates = rates;
		this.resourceUri = resourceUri;
		this.shortName = shortName;
		this.sourceId = sourceId;
		this.sourceUri = sourceUri;
		this.state = state;
		this.tags = tags;
		this.terms = terms;
		this.tips = tips;
		this.variants = variants;
		this.version = version;
	} 

	public EsRootJson() {
		super();
	} 
	public ArrayList<String> getAppliances() {
		return this.appliances;
	}

	public void setAppliances(ArrayList<String> appliances) {
		this.appliances = appliances;
	}
 
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
 
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
 
	public long getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(long difficulty) {
		this.difficulty = difficulty;
	}
 
	public boolean getFoodCooking() {
		return this.foodCooking;
	}

	public void setFoodCooking(boolean foodCooking) {
		this.foodCooking = foodCooking;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
 
	public String getMarket() {
		return this.market;
	}

	public void setMarket(String market) {
		this.market = market;
	}
 
	public ArrayList<String> getMedias() {
		return this.medias;
	}

	public void setMedias(ArrayList<String> medias) {
		this.medias = medias;
	}
 
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
 
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	public ArrayList<String> getPacks() {
		return this.packs;
	}

	public void setPacks(ArrayList<String> packs) {
		this.packs = packs;
	}
 
	public RatesJson getRates() {
		return this.rates;
	}

	public void setRates(RatesJson rates) {
		this.rates = rates;
	}
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
 
	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
 
	public String getSourceUri() {
		return this.sourceUri;
	}

	public void setSourceUri(String sourceUri) {
		this.sourceUri = sourceUri;
	}
 
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
 
	public ArrayList<TagsJson> getTags() {
		return this.tags;
	}

	public void setTags(ArrayList<TagsJson> tags) {
		this.tags = tags;
	}
 
	public ArrayList<String> getTerms() {
		return this.terms;
	}

	public void setTerms(ArrayList<String> terms) {
		this.terms = terms;
	}
 
	public String getTips() {
		return this.tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
 
	public ArrayList<VariantsJson> getVariants() {
		return this.variants;
	}

	public void setVariants(ArrayList<VariantsJson> variants) {
		this.variants = variants;
	}
 
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
 
	@Override
	public String toString() {
		final String s = "Object : EsRoot : "
		+ " appliances = " + appliances
		+ " created = " + created
		+ " createdBy = " + createdBy
		+ " desc = " + desc
		+ " difficulty = " + difficulty
		+ " foodCooking = " + foodCooking
		+ " idDb = " + idDb
		+ " lang = " + lang
		+ " market = " + market
		+ " medias = " + medias
		+ " modified = " + modified
		+ " modifiedBy = " + modifiedBy
		+ " name = " + name
		+ " packs = " + packs
		+ " rates = " + rates
		+ " resourceUri = " + resourceUri
		+ " shortName = " + shortName
		+ " sourceId = " + sourceId
		+ " sourceUri = " + sourceUri
		+ " state = " + state
		+ " tags = " + tags
		+ " terms = " + terms
		+ " tips = " + tips
		+ " variants = " + variants
		+ " version = " + version;
		return s;
	} 
} 

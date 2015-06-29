package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class RecipesJson { 
	@JsonProperty("tags")
	private ArrayList<TagsJson> tags; 
	//URIs to terms
	@JsonProperty("terms")
	private ArrayList<String> terms; 
	//URIs to appliances
	@JsonProperty("appliances")
	private ArrayList<String> appliances; 
	//URIs to medias
	@JsonProperty("medias")
	private ArrayList<String> medias; 
	//URIs to packs
	@JsonProperty("packs")
	private ArrayList<String> packs; 
	@JsonProperty("variants")
	private ArrayList<VariantsJson> variants; 
	@JsonProperty("modified")
	private Date modified; 
	@JsonProperty("created")
	private Date created; 
	@JsonProperty("rates")
	private RatesJson rates; 
	@JsonProperty("state")
	private String state; 
	@JsonProperty("short_name")
	private String shortName; 
	@JsonProperty("lang")
	private String lang; 
	@JsonProperty("version")
	private String version; 
	@JsonProperty("name")
	private String name; 
	@JsonProperty("desc")
	private String desc; 
	@JsonProperty("source_uri")
	private String sourceUri; 
	@JsonProperty("source_id")
	private String sourceId; 
	@JsonProperty("tips")
	private String tips; 
	//URI to users
	@JsonProperty("created_by")
	private String createdBy; 
	//URI to markets
	@JsonProperty("market")
	private String market; 
	//URI to users
	@JsonProperty("modified_by")
	private String modifiedBy; 
	//URI to recipes
	@JsonProperty("resource_uri")
	private String resourceUri; 
	@JsonProperty("food_cooking")
	private boolean foodCooking; 
	@JsonProperty("difficulty")
	private long difficulty; 
	@JsonProperty("id")
	private long idDb; 

	public RecipesJson(ArrayList<TagsJson> tags, ArrayList<String> terms, ArrayList<String> appliances, ArrayList<String> medias, ArrayList<String> packs, ArrayList<VariantsJson> variants, Date modified, Date created, RatesJson rates, String state, String shortName, String lang, String version, String name, String desc, String sourceUri, String sourceId, String tips, String createdBy, String market, String modifiedBy, String resourceUri, boolean foodCooking, long difficulty, long idDb) { 
		super();
		this.tags = tags;
		this.terms = terms;
		this.appliances = appliances;
		this.medias = medias;
		this.packs = packs;
		this.variants = variants;
		this.modified = modified;
		this.created = created;
		this.rates = rates;
		this.state = state;
		this.shortName = shortName;
		this.lang = lang;
		this.version = version;
		this.name = name;
		this.desc = desc;
		this.sourceUri = sourceUri;
		this.sourceId = sourceId;
		this.tips = tips;
		this.createdBy = createdBy;
		this.market = market;
		this.modifiedBy = modifiedBy;
		this.resourceUri = resourceUri;
		this.foodCooking = foodCooking;
		this.difficulty = difficulty;
		this.idDb = idDb;
	} 

	public RecipesJson() {
		super();
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
 
	public ArrayList<String> getAppliances() {
		return this.appliances;
	}

	public void setAppliances(ArrayList<String> appliances) {
		this.appliances = appliances;
	}
 
	public ArrayList<String> getMedias() {
		return this.medias;
	}

	public void setMedias(ArrayList<String> medias) {
		this.medias = medias;
	}
 
	public ArrayList<String> getPacks() {
		return this.packs;
	}

	public void setPacks(ArrayList<String> packs) {
		this.packs = packs;
	}
 
	public ArrayList<VariantsJson> getVariants() {
		return this.variants;
	}

	public void setVariants(ArrayList<VariantsJson> variants) {
		this.variants = variants;
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
 
	public RatesJson getRates() {
		return this.rates;
	}

	public void setRates(RatesJson rates) {
		this.rates = rates;
	}
 
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
 
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
 
	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
 
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
 
	public String getSourceUri() {
		return this.sourceUri;
	}

	public void setSourceUri(String sourceUri) {
		this.sourceUri = sourceUri;
	}
 
	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
 
	public String getTips() {
		return this.tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
 
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
 
	public boolean getFoodCooking() {
		return this.foodCooking;
	}

	public void setFoodCooking(boolean foodCooking) {
		this.foodCooking = foodCooking;
	}
 
	public long getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(long difficulty) {
		this.difficulty = difficulty;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Recipes : "
		+ " tags = " + tags
		+ " terms = " + terms
		+ " appliances = " + appliances
		+ " medias = " + medias
		+ " packs = " + packs
		+ " variants = " + variants
		+ " modified = " + modified
		+ " created = " + created
		+ " rates = " + rates
		+ " state = " + state
		+ " shortName = " + shortName
		+ " lang = " + lang
		+ " version = " + version
		+ " name = " + name
		+ " desc = " + desc
		+ " sourceUri = " + sourceUri
		+ " sourceId = " + sourceId
		+ " tips = " + tips
		+ " createdBy = " + createdBy
		+ " market = " + market
		+ " modifiedBy = " + modifiedBy
		+ " resourceUri = " + resourceUri
		+ " foodCooking = " + foodCooking
		+ " difficulty = " + difficulty
		+ " idDb = " + idDb;
		return s;
	} 
} 

package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Recipes {
 
	private ArrayList<Tags> tags; 
	//URIs to terms
	private ArrayList<String> terms; 
	//URIs to appliances
	private ArrayList<String> appliances; 
	//URIs to medias
	private ArrayList<String> medias; 
	//URIs to packs
	private ArrayList<String> packs; 
	private ArrayList<Variants> variants; 
	private Date modified; 
	private Date created; 
	private Rates rates; 
	private String state; 
	private String shortName; 
	private String lang; 
	private String version; 
	private String name; 
	private String desc; 
	private String sourceUri; 
	private String sourceId; 
	private String tips; 
	//URI to users
	private String createdBy; 
	//URI to markets
	private String market; 
	//URI to users
	private String modifiedBy; 
	//URI to recipes
	private String resourceUri; 
	private boolean foodCooking; 
	private long difficulty; 
	private long idDb; 

	public Recipes() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public ArrayList<Tags> getTags() {
		return this.tags;
	}

	public void setTags(ArrayList<Tags> tags) {
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
 
	public ArrayList<Variants> getVariants() {
		return this.variants;
	}

	public void setVariants(ArrayList<Variants> variants) {
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
 
	public Rates getRates() {
		return this.rates;
	}

	public void setRates(Rates rates) {
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

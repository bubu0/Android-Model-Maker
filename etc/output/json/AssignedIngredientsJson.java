package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class AssignedIngredientsJson { 
	@JsonProperty("desc_fr")
	private String descFr; 
	@JsonProperty("desc")
	private String desc; 
	@JsonProperty("desc_en")
	private String descEn; 
	@JsonProperty("desc_de")
	private String descDe; 
	@JsonProperty("desc_nl")
	private String descNl; 
	//URI to assigned_ingredients
	@JsonProperty("resource_uri")
	private String resourceUri; 
	//URI to steps
	@JsonProperty("step")
	private String step; 
	@JsonProperty("id")
	private long idDb; 
	@JsonProperty("order")
	private long orderDb; 

	public AssignedIngredientsJson(String descFr, String desc, String descEn, String descDe, String descNl, String resourceUri, String step, long idDb, long orderDb) { 
		super();
		this.descFr = descFr;
		this.desc = desc;
		this.descEn = descEn;
		this.descDe = descDe;
		this.descNl = descNl;
		this.resourceUri = resourceUri;
		this.step = step;
		this.idDb = idDb;
		this.orderDb = orderDb;
	} 

	public AssignedIngredientsJson() {
		super();
	} 
	public String getDescFr() {
		return this.descFr;
	}

	public void setDescFr(String descFr) {
		this.descFr = descFr;
	}
 
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
 
	public String getStep() {
		return this.step;
	}

	public void setStep(String step) {
		this.step = step;
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
		final String s = "Object : AssignedIngredients : "
		+ " descFr = " + descFr
		+ " desc = " + desc
		+ " descEn = " + descEn
		+ " descDe = " + descDe
		+ " descNl = " + descNl
		+ " resourceUri = " + resourceUri
		+ " step = " + step
		+ " idDb = " + idDb
		+ " orderDb = " + orderDb;
		return s;
	} 
} 

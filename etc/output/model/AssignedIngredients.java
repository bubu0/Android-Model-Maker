package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class AssignedIngredients {
 
	private String descFr; 
	private String desc; 
	private String descEn; 
	private String descDe; 
	private String descNl; 
	//URI to assigned_ingredients
	private String resourceUri; 
	//URI to steps
	private String step; 
	private long idDb; 
	private long orderDb; 

	public AssignedIngredients() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
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

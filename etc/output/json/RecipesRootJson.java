package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class RecipesRootJson { 

	@JsonProperty("deleted")	private ArrayList<DeletedJson> deleted; 

	@JsonProperty("meta")	private MetaJson meta; 

	@JsonProperty("objects")	private ArrayList<ObjectsJson> objects; 

	public RecipesRootJson(ArrayList<DeletedJson> deleted, MetaJson meta, ArrayList<ObjectsJson> objects) { 
		super();
		this.deleted = deleted;
		this.meta = meta;
		this.objects = objects;
	} 

	public RecipesRootJson() {
		super();
	} 
	public ArrayList<DeletedJson> getDeleted() {
		return this.deleted;
	}

	public void setDeleted(ArrayList<DeletedJson> deleted) {
		this.deleted = deleted;
	}
 
	public MetaJson getMeta() {
		return this.meta;
	}

	public void setMeta(MetaJson meta) {
		this.meta = meta;
	}
 
	public ArrayList<ObjectsJson> getObjects() {
		return this.objects;
	}

	public void setObjects(ArrayList<ObjectsJson> objects) {
		this.objects = objects;
	}
 
	@Override
	public String toString() {
		final String s = "Object : RecipesRoot : "
		+ " deleted = " + deleted
		+ " meta = " + meta
		+ " objects = " + objects;
		return s;
	} 
} 

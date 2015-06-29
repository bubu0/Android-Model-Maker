package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class RootRecipesJson { 
	@JsonProperty("deleted")
	private ArrayList<DeletedJson> deleted; 
	@JsonProperty("objects")
	private ArrayList<ObjectsJson> objects; 
	@JsonProperty("meta")
	private MetaJson meta; 

	public RootRecipesJson(ArrayList<DeletedJson> deleted, ArrayList<ObjectsJson> objects, MetaJson meta) { 
		super();
		this.deleted = deleted;
		this.objects = objects;
		this.meta = meta;
	} 

	public RootRecipesJson() {
		super();
	} 
	public ArrayList<DeletedJson> getDeleted() {
		return this.deleted;
	}

	public void setDeleted(ArrayList<DeletedJson> deleted) {
		this.deleted = deleted;
	}
 
	public ArrayList<ObjectsJson> getObjects() {
		return this.objects;
	}

	public void setObjects(ArrayList<ObjectsJson> objects) {
		this.objects = objects;
	}
 
	public MetaJson getMeta() {
		return this.meta;
	}

	public void setMeta(MetaJson meta) {
		this.meta = meta;
	}
 
	@Override
	public String toString() {
		final String s = "Object : RootRecipes : "
		+ " deleted = " + deleted
		+ " objects = " + objects
		+ " meta = " + meta;
		return s;
	} 
} 

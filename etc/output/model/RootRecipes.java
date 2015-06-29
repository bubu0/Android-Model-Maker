package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class RootRecipes {
 
	private ArrayList<Deleted> deleted; 
	private ArrayList<Objects> objects; 
	private Meta meta; 

	public RootRecipes() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public ArrayList<Deleted> getDeleted() {
		return this.deleted;
	}

	public void setDeleted(ArrayList<Deleted> deleted) {
		this.deleted = deleted;
	}
 
	public ArrayList<Objects> getObjects() {
		return this.objects;
	}

	public void setObjects(ArrayList<Objects> objects) {
		this.objects = objects;
	}
 
	public Meta getMeta() {
		return this.meta;
	}

	public void setMeta(Meta meta) {
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

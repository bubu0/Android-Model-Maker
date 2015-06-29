package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class PacksJson { 

	@JsonProperty("recipes")
	private RecipesJson recipes; 

	public PacksJson(RecipesJson recipes) { 
		super();
		this.recipes = recipes;
	} 

	public PacksJson() {
		super();
	} 
	public RecipesJson getRecipes() {
		return this.recipes;
	}

	public void setRecipes(RecipesJson recipes) {
		this.recipes = recipes;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Packs : "
		+ " recipes = " + recipes;
		return s;
	} 
} 

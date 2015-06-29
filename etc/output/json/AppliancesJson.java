package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class AppliancesJson { 

	@JsonProperty("recipes")
	private RecipesJson recipes; 

	public AppliancesJson(RecipesJson recipes) { 
		super();
		this.recipes = recipes;
	} 

	public AppliancesJson() {
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
		final String s = "Object : Appliances : "
		+ " recipes = " + recipes;
		return s;
	} 
} 
